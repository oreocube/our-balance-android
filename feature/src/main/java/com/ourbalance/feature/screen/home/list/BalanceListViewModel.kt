package com.ourbalance.feature.screen.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.balance.BalanceDetail
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.GetBalanceListUseCase
import com.ourbalance.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BalanceListViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getBalanceListUseCase: GetBalanceListUseCase
) : ViewModel() {
    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val userInfo = MutableStateFlow("")
    val isLogin = userInfo.map {
        it.isNotEmpty()
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    val balanceList = userInfo.map {
        if (it.isNotEmpty()) {
            fetchData(it)
        } else {
            listOf()
        }
    }

    val isEmpty = userInfo.combine(balanceList) { login, list ->
        login.isNotEmpty() and list.isEmpty()
    }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(500),
        scope = viewModelScope
    )

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        userInfo.update { result.data }
                    }
                    is Result.Error -> {
                        userInfo.update { "" }
                        Timber.d(result.message)
                    }
                }
            }
        }
    }

    private suspend fun fetchData(userInfo: String): List<BalanceDetail> {
        return when (val result = getBalanceListUseCase(userInfo)) {
            is Result.Success -> {
                Timber.d(result.data.toString())
                result.data
            }
            is Result.Error -> {
                Timber.d(this.message.toString())
                listOf()
            }
        }
    }
}
