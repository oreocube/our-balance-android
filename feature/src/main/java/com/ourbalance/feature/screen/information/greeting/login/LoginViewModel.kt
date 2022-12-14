package com.ourbalance.feature.screen.information.greeting.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result
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
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val signInUserUseCase: SignInUserUseCase
) : ViewModel() {

    val emailInput = MutableStateFlow("")
    val passwordInput = MutableStateFlow("")

    val isValidEmail: Flow<Boolean> = emailInput.map {
        validateEmailUseCase(it)
    }

    private val isValidPassword: Flow<Boolean> = passwordInput.map {
        validatePasswordUseCase(it)
    }

    val enable = combine(isValidEmail, isValidPassword) { isValidEmail, isValidPassword ->
        isValidEmail and isValidPassword
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    private val _successEvent = MutableSharedFlow<Unit>()
    val successEvent = _successEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

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
