package com.example.cdg_test.news_api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val API_KEY = "44956dbc6a204f02a788fce9f6619299"
    private val API_BASE_URL = "https://newsapi.org/v2/"

    private lateinit var errorHandler: ErrorApiHandler
    private lateinit var retrofit: Retrofit

    init {
        val gson = GsonBuilder().create()
        retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        errorHandler = ErrorApiHandler(retrofit)
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }


    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideLogInterceptor())
        .addInterceptor(provideAuthInterceptor())
        .build()

    private fun provideLogInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun provideAuthInterceptor() = Interceptor {chain->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)}
}