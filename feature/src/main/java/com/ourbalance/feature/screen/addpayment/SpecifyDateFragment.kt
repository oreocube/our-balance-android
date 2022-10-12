package com.ourbalance.feature.screen.addpayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.ourbalance.feature.constant.DATE_PICKER
import com.ourbalance.feature.databinding.FragmentSpecifyDateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecifyDateFragment : Fragment() {

    private var _binding: FragmentSpecifyDateBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<AddPaymentViewModel>()
    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpecifyDateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initDatePicker()
        binding.etDate.setOnClickListener {
            datePicker.show(parentFragmentManager, DATE_PICKER)
        }
    }

    private fun initDatePicker() {
        datePicker.addOnPositiveButtonClickListener {
            viewModel.setDate(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
