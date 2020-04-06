package com.example.cdg_test.manager

import androidx.lifecycle.MutableLiveData
import com.example.cdg_test.constants.Constants
import com.example.cdg_test.model.item_model.NewsItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class NewsSearchManager : AbstractManager(){
    val news: MutableLiveData<List<NewsItem>> = MutableLiveData()
    private lateinit var query: String
    private var totalPages: Int = 1
    private var currentPage: Int = 1
    private var pageIsLoading: Boolean = false

    fun proceedNewSearch(newQuery: String) {
        networkingScope.coroutineContext.cancel()
        query = newQuery
        currentPage = 1
        proceedSearch()
    }

    fun fetchNextPage(){
        if (pageIsLoading || currentPage == totalPages) return
        currentPage++
        proceedSearch()
    }

    private fun proceedSearch() = networkingScope.launch {
        pageIsLoading = true
        supervisorScope {
            try {
                newsRepo.fetchNewsByQuery(query, currentPage)?.let { response ->
                    totalPages = response.totalResults / (Constants.DEFAULT_FETCH_RESULT_SIZE + 1) + 1
                    val newList = news.value?.toMutableList() ?: mutableListOf()
                    newList.addAll(response.articles.map { NewsItem(it) })
                    news.postValue(newList)
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        pageIsLoading = false
    }
}