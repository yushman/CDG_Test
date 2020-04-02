package com.example.cdg_test.model.repo

import com.example.cdg_test.db.SourcesDao
import com.example.cdg_test.model.dto_model.SourcesDto
import com.example.cdg_test.news_api.NewsApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteSourcesRepo: KoinComponent{
    private val dao by inject<SourcesDao>()
//    val api by inject<NewsApiService>()

    fun loadFavorites() = dao.loadAllSources()

    fun saveToFavorites(source: SourcesDto) = dao.insertSource(source)

    fun removeFromFavorites(source: SourcesDto) = dao.deleteSource(source)
}