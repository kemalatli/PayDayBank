package com.paydaybank.android.di

import com.paydaybank.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides @Named("ServiceEndPoint")
    fun provideEndPoint() = BuildConfig.ServiceEndPoint

}