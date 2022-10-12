package com.ourbalance.di

import com.ourbalance.data.repository.AuthRepositoryImpl
import com.ourbalance.data.repository.BalanceRepositoryImpl
import com.ourbalance.data.repository.PaymentRepositoryImpl
import com.ourbalance.data.repository.UserRepositoryImpl
import com.ourbalance.domain.repository.AuthRepository
import com.ourbalance.domain.repository.BalanceRepository
import com.ourbalance.domain.repository.PaymentRepository
import com.ourbalance.domain.repository.UserRepository
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
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindsBalanceRepository(balanceRepositoryImpl: BalanceRepositoryImpl): BalanceRepository

    @Singleton
    @Binds
    abstract fun bindsPaymentRepository(paymentRepositoryImpl: PaymentRepositoryImpl): PaymentRepository
}
