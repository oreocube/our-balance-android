package com.ourbalance.di

import com.ourbalance.data.source.remote.auth.AuthDataSource
import com.ourbalance.data.source.remote.auth.AuthDataSourceImpl
import com.ourbalance.data.source.remote.balance.BalanceDataSource
import com.ourbalance.data.source.remote.balance.BalanceDataSourceImpl
import com.ourbalance.data.source.remote.payment.PaymentDataSource
import com.ourbalance.data.source.remote.payment.PaymentDataSourceImpl
import com.ourbalance.data.source.remote.user.UserDataSource
import com.ourbalance.data.source.remote.user.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Singleton
    @Binds
    abstract fun bindsUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Singleton
    @Binds
    abstract fun bindsBalanceDataSource(balanceDataSourceImpl: BalanceDataSourceImpl): BalanceDataSource

    @Singleton
    @Binds
    abstract fun bindsPaymentDataSource(paymentDataSourceImpl: PaymentDataSourceImpl): PaymentDataSource
}
