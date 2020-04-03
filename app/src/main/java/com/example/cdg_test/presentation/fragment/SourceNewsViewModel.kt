package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.SourceNewsManager
import com.example.cdg_test.model.item_model.NewsItem

class SourceNewsViewModel(sourceId: String) : ViewModel() {
    private val manager = SourceNewsManager(sourceId)

    val news: LiveData<List<NewsItem>> = manager.news

    fun fetchNews(page: Int = 1){
        manager.fetchNewsBySource(page)
    }
}
