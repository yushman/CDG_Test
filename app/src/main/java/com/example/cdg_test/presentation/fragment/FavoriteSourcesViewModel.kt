package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.FavoriteSourcesManager
import com.example.cdg_test.model.item_model.FavoritesItem

class FavoriteSourcesViewModel : ViewModel() {

    private val favoriteSourcesManager = FavoriteSourcesManager()
    val favorites = favoriteSourcesManager.favorites

    fun removeFromFavorites(favoritesItem: FavoritesItem) = favoriteSourcesManager.remmoveFromFavorites(favoritesItem)
}
