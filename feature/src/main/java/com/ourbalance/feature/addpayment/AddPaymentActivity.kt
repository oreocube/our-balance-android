package com.ourbalance.feature.addpayment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ourbalance.feature.databinding.ActivityAddPaymentBinding

class AddPaymentActivity : AppCompatActivity() {

    private var _binding: ActivityAddPaymentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
