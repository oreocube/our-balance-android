package com.ourbalance.feature.screen.information.greeting.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.RegisterUserUseCase
import com.ourbalance.domain.usecase.SignInUserUseCase
import com.ourbalance.domain.usecase.ValidateEmailUseCase
import com.ourbalance.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
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
    private val registerUserUseCase: RegisterUserUseCase,
    private val signInUserUseCase: SignInUserUseCase
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

    private val _successEvent = MutableSharedFlow<Unit>()
    val successEvent = _successEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    fun signup() {
        val userInfo = UserInfo(
            email = emailInput.value,
            password = passwordInput.value,
            userName = nicknameInput.value
        )

        viewModelScope.launch {
            when (val result = registerUserUseCase(userInfo)) {
                is Result.Success -> {
                    login()
                }
                is Result.Error -> {
                    Timber.d(result.message)
                    _message.emit(result.message)
                }
            }
        }
    }

    fun login() {
        val userInfo = UserInfo(
            email = emailInput.value,
            password = passwordInput.value
        )

        viewModelScope.launch {
            when (val result = signInUserUseCase(userInfo)) {
                is Result.Success -> {
                    _successEvent.emit(Unit)
                }
                is Result.Error -> {
                    Timber.d(result.message)
                    _message.emit(result.message)
                }
            }
        }
    }
}
