package com.ourbalance.di

import com.ourbalance.BuildConfig
import com.ourbalance.data.api.AuthService
import com.ourbalance.data.api.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @AuthOkHttpClient
    @Provides
    @Singleton
    fun providesAuthOkHttpClient() = OkHttpClient.Builder().build()

    @DefaultOkHttpClient
    @Provides
    @Singleton
    fun providesDefaultOkHttpClient(
        interceptor: TokenInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesAuthApi(
        @AuthOkHttpClient client: OkHttpClient
    ): AuthService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()

    @Provides
    @Singleton
    fun providesRetrofit(
        @DefaultOkHttpClient client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
