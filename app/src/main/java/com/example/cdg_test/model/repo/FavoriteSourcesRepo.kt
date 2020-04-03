package com.example.cdg_test.model.repo

import com.example.cdg_test.db.SourcesDao
import com.example.cdg_test.model.dto_model.SourcesDto
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteSourcesRepo: KoinComponent{
    private val dao by inject<SourcesDao>()
//    val api by inject<NewsApiService>()

    fun loadFavorites() = dao.loadAllSources()

    fun getFromFavoritesById(id: String) = dao.selectSourceById(id)

    fun saveToFavorites(source: SourcesDto) = dao.insertSource(source)

    fun removeFromFavorites(source: SourcesDto) = dao.deleteSource(source)
}