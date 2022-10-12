package com.ourbalance.feature.addpayment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.STEP_CHOOSE_PAYER
import com.ourbalance.feature.constant.STEP_CONFIRMATION
import com.ourbalance.feature.constant.STEP_SPECIFY_AMOUNT
import com.ourbalance.feature.constant.STEP_SPECIFY_CONTENT
import com.ourbalance.feature.constant.STEP_SPECIFY_DATE
import com.ourbalance.feature.databinding.ActivityAddPaymentBinding
import com.ourbalance.feature.ext.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AddPaymentActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AddPaymentViewModelFactory
    private var _binding: ActivityAddPaymentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: AddPaymentViewModel by viewModels {
        AddPaymentViewModel.provideFactory(
            assistedFactory = factory,
            balanceDetail = intent.getSerializableExtra(BALANCE_DETAIL) as BalanceDetail
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<ChoosePayerFragment>(R.id.fcv_payment, STEP_CHOOSE_PAYER)
            }
        }

        observeData()
    }

    private fun observeData() {
        viewModel.nextEvent.flowWithLifecycle(lifecycle).onEach {
            supportFragmentManager.commit {
                when (it) {
                    is PaymentUiState.PayerSelected -> {
                        replace<SpecifyAmountFragment>(R.id.fcv_payment, STEP_SPECIFY_AMOUNT)
                        addToBackStack(null)
                    }
                    is PaymentUiState.AmountSpecified -> {
                        replace<SpecifyContentFragment>(R.id.fcv_payment, STEP_SPECIFY_CONTENT)
                        addToBackStack(null)
                    }
                    is PaymentUiState.ContentSpecified -> {
                        replace<SpecifyDateFragment>(R.id.fcv_payment, STEP_SPECIFY_DATE)
                        addToBackStack(null)
                    }
                    is PaymentUiState.Completed -> {
                        replace<ConfirmationFragment>(R.id.fcv_payment, STEP_CONFIRMATION)
                        addToBackStack(null)
                    }
                    else -> {}
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.prevEvent.flowWithLifecycle(lifecycle).onEach {
            supportFragmentManager.popBackStack()
        }.launchIn(lifecycleScope)

        viewModel.closeEvent.flowWithLifecycle(lifecycle).onEach {
            finish()
        }.launchIn(lifecycleScope)

        viewModel.message.flowWithLifecycle(lifecycle).onEach {
            showToast(it)
        }.launchIn(lifecycleScope)

        viewModel.successEvent.flowWithLifecycle(lifecycle).onEach {
            finish()
        }.launchIn(lifecycleScope)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            viewModel.prev()
        } else {
            super.onBackPressed()
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
