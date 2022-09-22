package com.ourbalance.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ourbalance.data.source.local.DataStorePreferenceStorage.PreferencesKeys.PREF_USER_TOKEN
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
    }

    override val userToken = dataStore.data.map {
        it[PREF_USER_TOKEN] ?: ""
    }

    override suspend fun updateToken(token: String) {
        dataStore.edit {
            it[PREF_USER_TOKEN] = token
        }
    }
}
