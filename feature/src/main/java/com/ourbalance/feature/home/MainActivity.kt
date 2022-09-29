package com.ourbalance.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.ADD_BALANCE
import com.ourbalance.feature.constant.HOME
import com.ourbalance.feature.databinding.ActivityMainBinding
import com.ourbalance.feature.home.addbalance.AddBalanceFragment
import com.ourbalance.feature.home.list.BalanceListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        observeData()
    }

    private fun observeData() {
        viewModel.homeScreenState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                HOME -> {
                    supportFragmentManager.commit {
                        replace<BalanceListFragment>(R.id.fcv_home, HOME)
                    }
                }
                ADD_BALANCE -> {
                    supportFragmentManager.commit {
                        replace<AddBalanceFragment>(R.id.fcv_home, ADD_BALANCE)
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }
}
