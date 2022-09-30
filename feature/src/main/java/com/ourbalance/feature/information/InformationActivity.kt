package com.ourbalance.feature.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.GREETING
import com.ourbalance.feature.databinding.ActivityInformationBinding
import com.ourbalance.feature.information.greeting.GreetingFragment

class InformationActivity : AppCompatActivity() {
    private var _binding: ActivityInformationBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<GreetingFragment>(R.id.fcv_information, GREETING)
            }
        }
        initViews()
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
}
