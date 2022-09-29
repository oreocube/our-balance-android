package com.ourbalance.feature.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ourbalance.feature.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {
    private var _binding: ActivityInformationBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
