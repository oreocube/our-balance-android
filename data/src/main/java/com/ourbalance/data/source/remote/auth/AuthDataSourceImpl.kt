package com.ourbalance.data.source.remote.auth

import com.ourbalance.data.api.AuthService
import com.ourbalance.data.entity.mapper.toEntity
import com.ourbalance.data.ext.getResponse
import com.ourbalance.data.source.local.PreferenceStorage
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val prefs: PreferenceStorage
) : AuthDataSource {
    override suspend fun login(userInfo: UserInfo): Result<Unit> {
        return when (val response = getResponse(authService.login(userInfo.toEntity()))) {
            is Result.Success -> {
                saveToken(response.data)
                Result.Success(Unit)
            }
            is Result.Error -> response
        }
    }

    override suspend fun signup(userInfo: UserInfo): Result<Unit> {
        return getResponse(authService.signup(userInfo.toEntity()))
    }

    override suspend fun saveToken(token: String) {
        prefs.updateToken(token)
    }

    override suspend fun getToken() = prefs.userToken.first()

    override fun isLogin(): Flow<Boolean> {
        return prefs.userToken.map {
            it.isNotEmpty()
        }
    }

    override suspend fun logout() {
        prefs.updateUsername("")
        prefs.updateToken("")
    }
}
