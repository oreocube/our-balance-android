package com.ourbalance.feature.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ourbalance.feature.constant.HOME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _homeScreenState = MutableStateFlow(HOME)
    val homeScreenState = _homeScreenState.asSharedFlow()

    fun setScreenState(state: String) {
        viewModelScope.launch {
            _homeScreenState.emit(state)
        }
    }
}
