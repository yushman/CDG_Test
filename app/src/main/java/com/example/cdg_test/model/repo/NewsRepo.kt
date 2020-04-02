package com.example.cdg_test.model.repo

import com.example.cdg_test.db.NewsDao
import com.example.cdg_test.model.dto_model.NewsDto
import com.example.cdg_test.news_api.NewsApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsRepo: KoinComponent{
    val dao by inject<NewsDao>()
    private val api by inject<NewsApiService>()

    suspend fun fetchNewsByQuery(q: String, page: Int = 1) = api.getNewsByQuery(q, page).articles

    suspend fun fetchNewsBySource(sourceId: String, page: Int = 1) = api.getNewsBySource(sourceId, page).articles

    fun loadNewsBySources(sourceId: String) = dao.loadAllNews()

    fun updateNewsInDb(news: List<NewsDto>) = news.forEach { dao.insertNews(it) }
}