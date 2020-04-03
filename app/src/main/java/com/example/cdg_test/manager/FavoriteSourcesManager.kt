package com.example.cdg_test.manager

import androidx.lifecycle.MediatorLiveData
import com.example.cdg_test.model.dto_model.SourcesDto
import com.example.cdg_test.model.item_model.FavoritesItem
import kotlinx.coroutines.launch

class FavoriteSourcesManager: AbstractManager() {

    val favorites: MediatorLiveData<List<FavoritesItem>> = MediatorLiveData()
    private val favoritesDto = favoriteSourcesRepo.loadFavorites()

    init {
        favorites.addSource(favoritesDto){ postFavorites(it) }
    }

    fun remmoveFromFavorites(favoritesItem: FavoritesItem) = persistanceScope.launch {
        favoriteSourcesRepo.removeFromFavorites(favoritesItem.source)
    }

    private fun postFavorites(favoritesDto: List<SourcesDto>?) = persistanceScope.launch {
        favoritesDto?.let { list ->
            favorites.postValue(list.map { FavoritesItem(it) })
        }
    }
}