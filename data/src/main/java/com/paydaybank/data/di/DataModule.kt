package com.paydaybank.data.di

import android.content.Context
import androidx.room.Room
import com.paydaybank.data.offline.AppDatabase
import com.paydaybank.data.service.PayDayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        @Named("ServiceEndPoint") serviceEndpoint:String) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(serviceEndpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun providePayBankService(retrofit:Retrofit) = retrofit.create(PayDayService::class.java)


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "payday").build()


}