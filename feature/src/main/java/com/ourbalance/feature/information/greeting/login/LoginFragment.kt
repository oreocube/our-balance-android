package com.ourbalance.feature.information.greeting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.SIGNUP
import com.ourbalance.feature.databinding.FragmentLoginBinding
import com.ourbalance.feature.information.greeting.signup.SignupFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)
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
        tvSignup.setOnClickListener {
            parentFragmentManager.commit {
                replace<SignupFragment>(R.id.fcv_information, SIGNUP)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
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
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
