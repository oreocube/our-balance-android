package com.ourbalance.feature.addpayment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.feature.R
import com.ourbalance.feature.databinding.ActivityAddPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPaymentActivity : AppCompatActivity() {

    private var _binding: ActivityAddPaymentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<ChoosePayerFragment>(R.id.fcv_payment, "payer")
            }
        }
    }

    companion object {
        const val BALANCE_DETAIL = "BALANCE_DETAIL"

        fun newIntent(context: Context, balanceDetail: BalanceDetail) =
            Intent(context, AddPaymentActivity::class.java).apply {
                putExtra(BALANCE_DETAIL, balanceDetail)
            }
    }
}
