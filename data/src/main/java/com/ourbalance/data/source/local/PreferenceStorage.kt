package com.ourbalance.data.source.local

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    val userToken: Flow<String>
    val username: Flow<String>
    suspend fun updateToken(token: String)
    suspend fun updateUsername(name: String)
}
