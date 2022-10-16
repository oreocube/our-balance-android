package com.ourbalance.feature.screen.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.domain.result.Result
import com.ourbalance.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfo = MutableStateFlow("")
    val userInfo = _userInfo.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _userInfo.update { result.data }
                    }
                    is Result.Error -> {
                        _userInfo.update { "" }
                        Timber.d(result.message)
                    }
                }
            }
        }
    }
}
