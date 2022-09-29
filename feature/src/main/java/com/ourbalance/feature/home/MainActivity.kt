package com.ourbalance.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ourbalance.feature.databinding.ActivityMainBinding
import com.ourbalance.feature.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { BalanceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
        initViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        rvBalanceList.adapter = adapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.message.collectLatest {
                        showToast(it)
                    }
                }
                launch {
                    viewModel.balanceList.collectLatest {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }
}
