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
    private val viewModel: BalanceDetailViewModel by viewModels {
        BalanceDetailViewModel.provideFactory(
            assistedFactory = factory,
            balanceId = requireArguments().getLong(BALANCE_ID),
            userName = requireArguments().getString(USER_NAME) ?: ""
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
                    viewModel.message.collect {
                        requireContext().showToast(it)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BALANCE_ID = "BALANCE_ID"
        const val USER_NAME = "USER_NAME"
    }
}
