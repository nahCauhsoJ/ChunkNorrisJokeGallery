package com.example.chunknorrisjokegallery.di

import com.example.chunknorrisjokegallery.network.APIService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun gson(): Gson = Gson()

    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(APIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun apiService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}