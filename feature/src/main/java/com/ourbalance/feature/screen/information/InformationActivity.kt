package com.ourbalance.feature.screen.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.GREETING
import com.ourbalance.feature.constant.PROFILE
import com.ourbalance.feature.constant.USER_NAME
import com.ourbalance.feature.databinding.ActivityInformationBinding
import com.ourbalance.feature.screen.information.greeting.GreetingFragment
import com.ourbalance.feature.screen.information.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class InformationActivity : AppCompatActivity() {
    private var _binding: ActivityInformationBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<InformationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<GreetingFragment>(R.id.fcv_information, GREETING)
            }
        }
        initViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        ibtBack.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    private fun observeData() {
        viewModel.userInfo.flowWithLifecycle(lifecycle).onEach { username ->
            if (username.isNotEmpty()) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ProfileFragment>(
                        R.id.fcv_information,
                        PROFILE,
                        args = bundleOf(USER_NAME to username)
                    )
                }
            } else {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<GreetingFragment>(R.id.fcv_information, GREETING)
                }
            }
        }.launchIn(lifecycleScope)
    }
}
