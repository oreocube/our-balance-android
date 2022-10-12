package com.ourbalance.data.source.remote.user

import com.ourbalance.data.api.UserService
import com.ourbalance.data.ext.getResponse
import com.ourbalance.data.source.local.PreferenceStorage
import com.ourbalance.domain.result.Result
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val service: UserService,
    private val prefs: PreferenceStorage
) : UserDataSource {

    override suspend fun getUserInfo(): Result<String> {
        val response = getResponse(service.getUser())
        if (response is Result.Success) {
            saveUserInfo(response.data)
        }
        return response
    }

    override suspend fun saveUserInfo(name: String) {
        prefs.updateUsername(name)
    }
}
