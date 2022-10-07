package com.ourbalance.feature.home.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.model.BalanceDetail
import com.ourbalance.domain.model.BalanceDetailInfo
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.GetBalanceDetailUseCase
import com.ourbalance.feature.home.balance.BalanceDetailViewModel.Companion.BALANCE_ID
import com.ourbalance.feature.home.balance.BalanceDetailViewModel.Companion.USER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class BalanceDetailViewModel @AssistedInject constructor(
    @Assisted(BALANCE_ID) private val balanceId: Long,
    @Assisted(USER_NAME) private val userName: String,
    private val getBalanceDetailUseCase: GetBalanceDetailUseCase
) : ViewModel() {

    val balanceDetailItem = MutableStateFlow<BalanceDetail?>(null)
    val user = MutableStateFlow(userName)
    val otherName = MutableStateFlow("")
    val diff = MutableStateFlow<Long>(0)

    private val _closeEvent = MutableSharedFlow<Unit>()
    val closeEvent = _closeEvent.asSharedFlow()

    private val _addEvent = MutableSharedFlow<Unit>()
    val addEvent = _addEvent.asSharedFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    private val info by lazy {
        BalanceDetailInfo(
            userName = userName,
            balanceId = balanceId
        )
    }

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            when (val result = getBalanceDetailUseCase(info)) {
                is Result.Success -> {
                    result.data.also { item ->
                        Timber.d(item.toString())
                        balanceDetailItem.value = item

                        if (item.isFull) {
                            otherName.value = item.others[0].userName
                            diff.value = item.me.amount - item.others[0].amount
                        }
                    }
                }
                is Result.Error -> {
                    Timber.d("실패")
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

    fun add() {
        viewModelScope.launch {
            _addEvent.emit(Unit)
        }
    }

    companion object {
        const val BALANCE_ID = "BALANCE_ID"
        const val USER_NAME = "USER_NAME"

        fun provideFactory(
            assistedFactory: BalanceDetailViewModelFactory,
            balanceId: Long,
            userName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(balanceId, userName) as T
            }
        }
    }
}

@AssistedFactory
interface BalanceDetailViewModelFactory {
    fun create(
        @Assisted(BALANCE_ID) balanceId: Long,
        @Assisted(USER_NAME) userName: String
    ): BalanceDetailViewModel
}
