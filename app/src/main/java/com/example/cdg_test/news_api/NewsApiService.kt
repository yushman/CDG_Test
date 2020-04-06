package com.example.cdg_test.news_api

import com.example.cdg_test.model.dto_model.NewsResponseDto
import com.example.cdg_test.model.dto_model.NewsSourcesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService{

    @GET("sources")
    suspend fun getAllSources(): NewsSourcesResponseDto?

    @GET("everything")
    suspend fun getNewsByQuery(@Query("q") q: String, @Query("page") page:Int): NewsResponseDto?

    @GET("everything")
    suspend fun getNewsBySource(@Query("sources") sourceId: String, @Query("page") page:Int): NewsResponseDto?
}