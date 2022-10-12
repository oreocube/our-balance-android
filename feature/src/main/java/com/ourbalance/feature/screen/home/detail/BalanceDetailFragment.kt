package com.ourbalance.feature.screen.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.feature.constant.BALANCE_DETAIL
import com.ourbalance.feature.databinding.FragmentBalanceDetailBinding
import com.ourbalance.feature.ext.showToast
import com.ourbalance.feature.screen.addpayment.AddPaymentActivity
import com.ourbalance.feature.screen.payment.PaymentListActivity
import com.ourbalance.feature.screen.payment.PaymentSummary
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BalanceDetailFragment : Fragment() {

    @Inject
    lateinit var factory: BalanceDetailViewModelFactory
    private var _binding: FragmentBalanceDetailBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val balanceDetail by lazy { requireArguments().get(BALANCE_DETAIL) as BalanceDetail }
    private val viewModel: BalanceDetailViewModel by viewModels {
        BalanceDetailViewModel.provideFactory(
            assistedFactory = factory,
            balanceId = balanceDetail.roomId,
            userName = balanceDetail.me.userName
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBalanceDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        observeData()
    }

    private fun initViews() {
        if (balanceDetail.isFull) {
            binding.viewMe.setOnClickListener {
                navigateToPaymentList(true)
            }
            binding.viewOther.setOnClickListener {
                navigateToPaymentList(false)
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.closeEvent.collect {
                        parentFragmentManager.popBackStack()
                    }
                }
                launch {
                    viewModel.addEvent.collect {
                        navigateToAddPayment(balanceDetail)
                    }
                }
                launch {
                    viewModel.message.collect {
                        requireContext().showToast(it)
                    }
                }
            }
        }
    }

    private fun navigateToAddPayment(balanceDetail: BalanceDetail) {
        startActivity(
            AddPaymentActivity.newIntent(
                context = requireContext(),
                balanceDetail = balanceDetail
            )
        )
    }

    private fun navigateToPaymentList(byMe: Boolean) {
        startActivity(
            PaymentListActivity.newIntent(
                requireContext(),
                getSummary(byMe, balanceDetail)
            )
        )
    }

    private fun getSummary(byMe: Boolean, balanceDetail: BalanceDetail): PaymentSummary {
        return balanceDetail.run {
            val payer = if (byMe) me else others[0]

            PaymentSummary(
                isMe = byMe,
                balanceId = roomId,
                payerId = payer.participantId,
                username = payer.userName,
                total = total,
                amount = payer.amount,
                ratio = payer.ratio
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
