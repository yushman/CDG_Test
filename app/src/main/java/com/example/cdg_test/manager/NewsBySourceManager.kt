package com.example.cdg_test.manager

import androidx.lifecycle.MediatorLiveData
import com.example.cdg_test.constants.Constants
import com.example.cdg_test.model.dto_model.NewsDto
import com.example.cdg_test.model.item_model.NewsItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class NewsBySourceManager(private val sourceId: String): AbstractManager() {

    val news: MediatorLiveData<List<NewsItem>> = MediatorLiveData()
    private val newsDto = newsRepo.loadNewsBySources(sourceId)
    private var totalPages: Int = 1
    private var currentPage: Int = 1
    private var pageIsLoading: Boolean = false

    init {
        news.addSource(newsDto){ postNews(it) }
    }

    fun fetchNewsBySource() = networkingScope.launch {
        pageIsLoading = true
        supervisorScope {
            try {
                newsRepo.fetchNewsBySource(sourceId, currentPage)?.let { response ->
                    totalPages = response.totalResults / (Constants.DEFAULT_FETCH_RESULT_SIZE + 1) + 1
                    persistanceScope.launch { newsRepo.updateNewsInDb(response.articles) }
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        pageIsLoading = false
    }

    fun fetchNewPage() {
        if (pageIsLoading || currentPage == totalPages) return
        currentPage++
        fetchNewsBySource()
    }

    private fun postNews(newsDto: List<NewsDto>?) = persistanceScope.launch {
        news.postValue(newsDto?.map { NewsItem(it) })
    }



}