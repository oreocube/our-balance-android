package com.ourbalance.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.CheckLoginUseCase
import com.ourbalance.domain.usecase.GetBalanceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoginUseCase: CheckLoginUseCase,
    private val getBalanceListUseCase: GetBalanceListUseCase
) : ViewModel() {

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val _isLogin = MutableStateFlow(false)
    val isLogin = _isLogin.asStateFlow()

    private val _balanceList = MutableStateFlow<List<BalanceDetail>>(listOf())
    val balanceList = _balanceList.asStateFlow()

    val isEmpty = isLogin.combine(balanceList) { login, list ->
        login and list.isEmpty()
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    init {
        viewModelScope.launch {
            _isLogin.value = checkLoginUseCase()
            if (isLogin.value) {
                fetchData()
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            getBalanceListUseCase(10).run {
                when (this) {
                    is Result.Success -> {
                        _balanceList.value = this.data
                    }
                    is Result.Error -> {
                        _message.emit(this.message)
                    }
                }
            }
        }
    }
}