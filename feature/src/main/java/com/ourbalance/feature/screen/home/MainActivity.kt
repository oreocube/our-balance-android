package com.ourbalance.feature.screen.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.HOME
import com.ourbalance.feature.databinding.ActivityMainBinding
import com.ourbalance.feature.screen.home.list.BalanceListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<BalanceListFragment>(R.id.fcv_home, HOME)
            }
        }
    }
}
