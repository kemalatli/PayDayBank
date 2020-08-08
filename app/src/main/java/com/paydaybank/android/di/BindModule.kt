package com.paydaybank.android.di

import com.paydaybank.android.init.TimberInitializer
import com.paydaybank.data.init.AppInitializer
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.transaction.impl.DefaultTransactionRepository
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.impl.DefaultUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @IntoSet
    @Binds
    abstract fun bindTimberInitializer(timberInitializer: TimberInitializer): AppInitializer

}