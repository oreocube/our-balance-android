package com.ourbalance.feature.home.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ourbalance.feature.constant.ADD_BALANCE_BOTTOM_SHEET
import com.ourbalance.feature.databinding.FragmentBalanceListBinding
import com.ourbalance.feature.home.BalanceAdapter
import com.ourbalance.feature.information.InformationActivity
import com.ourbalance.feature.view.AddBalanceBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BalanceListFragment : Fragment() {

    private var _binding: FragmentBalanceListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val balanceListViewModel by viewModels<BalanceListViewModel>()
    private val adapter by lazy { BalanceAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBalanceListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = balanceListViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        rvBalanceList.adapter = adapter
        ibtSetting.setOnClickListener {
            startActivity(Intent(requireContext(), InformationActivity::class.java))
        }
        fabAdd.setOnClickListener {
            AddBalanceBottomSheet.newInstance()
                .show(parentFragmentManager, ADD_BALANCE_BOTTOM_SHEET)
        }
    }

    private fun observeData() = with(balanceListViewModel) {
        balanceList.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { adapter.submitList(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
