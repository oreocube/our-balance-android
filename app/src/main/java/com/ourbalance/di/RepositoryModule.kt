package com.ourbalance.di

import com.ourbalance.data.repository.AuthRepositoryImpl
import com.ourbalance.data.repository.BalanceRepositoryImpl
import com.ourbalance.domain.repository.AuthRepository
import com.ourbalance.domain.repository.BalanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindsBalanceRepository(balanceRepositoryImpl: BalanceRepositoryImpl): BalanceRepository
}
