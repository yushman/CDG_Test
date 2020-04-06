package com.example.cdg_test.model.repo

import com.example.cdg_test.news_api.NewsApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class SourcesRepo: KoinComponent{
    private val api by inject<NewsApiService>()

    suspend fun fetchSources() = api.getAllSources()?.sources
}