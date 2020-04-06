package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.NewsSearchManager

class NewsSearchViewModel : ViewModel() {
    private val manager = NewsSearchManager()
    val news = manager.news

    fun proceedNewSearch(query: String) = manager.proceedNewSearch(query)

    fun fetchNextPage() = manager.fetchNextPage()
}
