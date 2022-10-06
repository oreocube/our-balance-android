package com.ourbalance.feature.home.addbalance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.RoomInfo
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.AddBalanceUseCase
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
class AddBalanceViewModel @Inject constructor(
    private val addBalanceUseCase: AddBalanceUseCase
) : ViewModel() {

    val title = MutableStateFlow("")
    val enable = title.map { it.length > 2 }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    fun add() {
        viewModelScope.launch {
            when (val result = addBalanceUseCase(RoomInfo(title = title.value))) {
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
