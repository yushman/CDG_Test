package com.example.cdg_test.model.repo

import com.example.cdg_test.db.NewsDao
import com.example.cdg_test.model.dto_model.NewsDto
import com.example.cdg_test.news_api.NewsApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsRepo: KoinComponent{
    private val dao by inject<NewsDao>()
    private val api by inject<NewsApiService>()

    suspend fun fetchNewsByQuery(q: String, page: Int) = api.getNewsByQuery(q, page)

    suspend fun fetchNewsBySource(sourceId: String, page: Int) = api.getNewsBySource(sourceId, page)

    fun loadNewsBySources(sourceId: String) = dao.loadNewsBySource(sourceId)

    fun updateNewsInDb(news: List<NewsDto>) = news.forEach { dao.insertNews(it) }
}