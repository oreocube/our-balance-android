package com.ourbalance.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.ourbalance.data.source.local.DataStorePreferenceStorage
import com.ourbalance.data.source.local.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesStorageModule {

    val Context.datastore by preferencesDataStore(
        name = DataStorePreferenceStorage.PREFS_NAME
    )

    @Singleton
    @Provides
    fun providesPreferenceStorage(@ApplicationContext context: Context): PreferenceStorage =
        DataStorePreferenceStorage(context.datastore)
}
