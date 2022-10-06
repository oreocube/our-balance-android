package com.ourbalance.feature.home.addbalance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.ParticipationInfo
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.ParticipateInBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddBalanceCodeViewModel @Inject constructor(
    private val participateInBalanceUseCase: ParticipateInBalanceUseCase
) : ViewModel() {

    val code = MutableStateFlow("")
    val enable = code.map { it.length == 6 }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    fun participate() {
        val info = ParticipationInfo(
            roomId = 10,
            password = code.value
        )

        viewModelScope.launch {
            when (val result = participateInBalanceUseCase(info)) {
                is Result.Success -> {
                    close()
                }
                is Result.Error -> {
                    Timber.d(result.message)
                    _message.emit(result.message)
                }
            }
        }
    }

    fun close() {
        viewModelScope.launch {
            _closeEvent.emit(Unit)
        }
    }
}
