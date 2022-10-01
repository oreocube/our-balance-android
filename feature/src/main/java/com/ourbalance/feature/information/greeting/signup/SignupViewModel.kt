package com.ourbalance.feature.information.greeting.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.usecase.RegisterUserUseCase
import com.ourbalance.domain.usecase.ValidateEmailUseCase
import com.ourbalance.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    val nicknameInput = MutableStateFlow("")
    val emailInput = MutableStateFlow("")
    val passwordInput = MutableStateFlow("")
    val passwordConfirmInput = MutableStateFlow("")

    val isValidEmail: Flow<Boolean> = emailInput.map {
        validateEmailUseCase(it)
    }

    val isValidPassword: Flow<Boolean> = passwordInput.map {
        validatePasswordUseCase(it)
    }

    val isSamePassword = combine(passwordInput, passwordConfirmInput) { p1, p2 ->
        if (p1.isNotEmpty() and p2.isNotEmpty()) {
            p1 == p2
        } else {
            null
        }
    }

    val enable = combine(
        isValidEmail,
        isValidPassword,
        isSamePassword
    ) { isValidEmail, isValidPassword, isSamePassword ->
        if (isSamePassword != null) {
            isValidEmail and isValidPassword and isSamePassword
        } else {
            false
        }
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    fun signup() {
        viewModelScope.launch {
            Timber.d("회원가입")
        }
    }
}
