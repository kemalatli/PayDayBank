package com.paydaybank.data.di

import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.impl.DefaultUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindUserRepository(defaultUserRepository: DefaultUserRepository): UserRepository

}