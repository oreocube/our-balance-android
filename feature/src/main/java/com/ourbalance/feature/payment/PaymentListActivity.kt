package com.ourbalance.feature.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import com.ourbalance.feature.databinding.ActivityPaymentListBinding
import com.ourbalance.feature.payment.adapter.PaymentPagingDataAdapter
import com.ourbalance.feature.payment.adapter.PaymentSummaryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaymentListActivity : AppCompatActivity() {

    private var _binding: ActivityPaymentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<PaymentListViewModel>()

    private val paymentSummary by lazy {
        intent.getSerializableExtra(SUMMARY) as PaymentSummary
    }
    private val paymentSummaryAdapter by lazy {
        PaymentSummaryAdapter().apply { setData(paymentSummary) }
    }
    private val paymentPagingAdapter by lazy { PaymentPagingDataAdapter() }
    private val concatAdapter by lazy { ConcatAdapter(paymentSummaryAdapter, paymentPagingAdapter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        initViews()
        observeData()
    }

    private fun initViews() {
        with(binding) {
            username = paymentSummary.username
            rvPaymentList.adapter = concatAdapter
            btnClose.setOnClickListener { finish() }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.pagingData.collectLatest {
                        paymentPagingAdapter.submitData(it)
                    }
                }
            }
        }
    }

    companion object {
        const val SUMMARY = "SUMMARY"

        fun newIntent(context: Context, summary: PaymentSummary) =
            Intent(context, PaymentListActivity::class.java).apply {
                putExtra(SUMMARY, summary)
            }
    }
}
