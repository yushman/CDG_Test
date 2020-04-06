package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.NewsBySourceManager
import com.example.cdg_test.model.item_model.NewsItem

class NewsBySourceViewModel(sourceId: String) : ViewModel() {
    private val manager = NewsBySourceManager(sourceId)

    val news: LiveData<List<NewsItem>> = manager.news

    fun fetchNews(){
        manager.fetchNewsBySource()
    }

    fun fetchNewPage() {
        manager.fetchNewPage()
    }
}
