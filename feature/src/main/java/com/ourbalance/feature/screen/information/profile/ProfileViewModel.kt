package com.ourbalance.feature.screen.information.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    fun logout() {
        viewModelScope.launch {
            runCatching {
                authRepository.logout()
            }.onSuccess {
                _closeEvent.emit(Unit)
            }.onFailure {
                Timber.d(it.message)
            }
        }
    }
}
