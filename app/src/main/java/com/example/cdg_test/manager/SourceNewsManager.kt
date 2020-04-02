package com.example.cdg_test.manager

import androidx.lifecycle.MutableLiveData
import com.example.cdg_test.di.NetworkingScope
import com.example.cdg_test.di.PersistanceScope
import com.example.cdg_test.model.item_model.SourcesItem
import com.example.cdg_test.model.repo.FavoriteSourcesRepo
import com.example.cdg_test.model.repo.SourcesRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SourceNewsManager(sourcesItem: SourcesItem): AbstractManager() {
    private val sourceId = sourcesItem.source.id
    val news = newsRepo.loadNewsBySources(sourceId)

    fun fetchNewsBySource(page: Int = 1) = networkingScope.launch {
        val news = newsRepo.fetchNewsBySource(sourceId, page)
        persistanceScope.launch { newsRepo.updateNewsInDb(news) }
    }

}