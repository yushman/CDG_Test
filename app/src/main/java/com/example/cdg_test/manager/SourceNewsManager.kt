package com.example.cdg_test.manager

import androidx.lifecycle.MediatorLiveData
import com.example.cdg_test.model.dto_model.NewsDto
import com.example.cdg_test.model.item_model.NewsItem
import kotlinx.coroutines.launch

class SourceNewsManager(private val sourceId: String): AbstractManager() {

    val news: MediatorLiveData<List<NewsItem>> = MediatorLiveData()
    private val newsDto = newsRepo.loadNewsBySources(sourceId)

    init {
        news.addSource(newsDto){ postNews(it) }
    }

    private fun postNews(newsDto: List<NewsDto>?) = persistanceScope.launch {
        news.postValue(newsDto?.map { NewsItem(it) })
    }

    fun fetchNewsBySource(page: Int = 1) = networkingScope.launch {
        val news = newsRepo.fetchNewsBySource(sourceId, page)
        persistanceScope.launch { newsRepo.updateNewsInDb(news) }
    }

}