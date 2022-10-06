package com.ourbalance.feature.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.ADD_BALANCE
import com.ourbalance.feature.constant.ADD_BALANCE_CODE
import com.ourbalance.feature.databinding.BottomSheetAddBalanceBinding
import com.ourbalance.feature.home.addbalance.AddBalanceCodeFragment
import com.ourbalance.feature.home.addbalance.AddBalanceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBalanceBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddBalanceBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAddBalanceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        btnAdd.root.setOnClickListener {
            parentFragmentManager.commit {
                replace<AddBalanceFragment>(R.id.fcv_home, ADD_BALANCE)
                addToBackStack(null)
            }
            dismiss()
        }
        btnAddByCode.root.setOnClickListener {
            parentFragmentManager.commit {
                replace<AddBalanceCodeFragment>(R.id.fcv_home, ADD_BALANCE_CODE)
                addToBackStack(null)
            }
            dismiss()
        }
        btnClose.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AddBalanceBottomSheet()
    }
}
