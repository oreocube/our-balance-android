package com.ourbalance.feature.information.greeting.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ourbalance.feature.R
import com.ourbalance.feature.constant.SIGNUP
import com.ourbalance.feature.databinding.FragmentLoginBinding
import com.ourbalance.feature.information.greeting.signup.SignupFragment

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = requireNotNull(_binding)

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
        initViews()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
