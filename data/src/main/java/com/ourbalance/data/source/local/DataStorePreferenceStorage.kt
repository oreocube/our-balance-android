package com.ourbalance.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ourbalance.data.source.local.DataStorePreferenceStorage.PreferencesKeys.PREF_USER_NAME
import com.ourbalance.data.source.local.DataStorePreferenceStorage.PreferencesKeys.PREF_USER_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {

    companion object {
        const val PREFS_NAME = "our_balance"
    }

    object PreferencesKeys {
        val PREF_USER_TOKEN = stringPreferencesKey("pref_user_token")
        val PREF_USER_NAME = stringPreferencesKey("pref_user_name")
    }

    override val userToken: Flow<String> = dataStore.data.map {
        it[PREF_USER_TOKEN] ?: ""
    }

    override val username: Flow<String> = dataStore.data.map {
        it[PREF_USER_NAME] ?: ""
    }

    override suspend fun updateToken(token: String) {
        dataStore.edit {
            it[PREF_USER_TOKEN] = token
        }
    }

    override suspend fun updateUsername(name: String) {
        dataStore.edit {
            it[PREF_USER_NAME] = name
        }
    }
}
