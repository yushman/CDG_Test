package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.SourceNewsManager
import com.example.cdg_test.model.item_model.SourcesItem

class SourceNewsViewModel(sourcesItem: SourcesItem) : ViewModel() {
    private val manager = SourceNewsManager(sourcesItem)

    val news = manager.news

    fun fetchNews(page: Int){
        manager.fetchNewsBySource(page)
    }
}
