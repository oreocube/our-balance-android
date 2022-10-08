package com.ourbalance.feature.addpayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ourbalance.feature.databinding.FragmentSpecifyContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecifyContentFragment : Fragment() {

    private var _binding: FragmentSpecifyContentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<AddPaymentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpecifyContentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
