package com.paydaybank.data.di

import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.impl.DefaultUserRepository
import com.paydaybank.data.service.PayBankService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor (HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .readTimeout(30L, TimeUnit.SECONDS)
        .writeTimeout(30L, TimeUnit.SECONDS)
        .callTimeout(20L, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, @Named("ServiceEndPoint") serviceEndpoint:String) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(serviceEndpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun providePayBankService(retrofit:Retrofit) = retrofit.create(PayBankService::class.java)


    @Singleton
    @Provides
    fun provideUserRepository():UserRepository = DefaultUserRepository()



}