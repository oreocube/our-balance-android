package com.ourbalance.feature.home.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.feature.addpayment.AddPaymentActivity
import com.ourbalance.feature.constant.BALANCE_DETAIL
import com.ourbalance.feature.databinding.FragmentBalanceDetailBinding
import com.ourbalance.feature.ext.showToast
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
        observeData()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
