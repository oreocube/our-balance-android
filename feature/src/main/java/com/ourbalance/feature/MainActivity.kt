package com.ourbalance.feature

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { BalanceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                vm = viewModel
                lifecycleOwner = this@MainActivity
            }

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
