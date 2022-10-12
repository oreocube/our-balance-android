package com.ourbalance.feature.screen.information.greeting.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ourbalance.feature.R
import com.ourbalance.feature.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        tvLogin.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeData() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isValidEmail.collectLatest { isValid ->
                        tilEmail.error = if (isValid or etEmail.text.toString().isEmpty()) {
                            null
                        } else {
                            getString(R.string.error_email_format)
                        }
                    }
                }
                launch {
                    viewModel.isValidPassword.collectLatest { isValid ->
                        tilPassword.error = if (isValid or etPassword.text.toString().isEmpty()) {
                            null
                        } else {
                            getString(R.string.error_password_format)
                        }
                    }
                }
                launch {
                    viewModel.isSamePassword.collectLatest { isValid ->
                        tilConfirmPassword.error = isValid?.let {
                            if (it) {
                                null
                            } else {
                                getString(R.string.error_password_check)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
