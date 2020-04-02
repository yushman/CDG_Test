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

class SourcesManager: AbstractManager() {
    val sources: MutableLiveData<List<SourcesItem>> = MutableLiveData()

    fun fetchSources() = networkingScope.launch {
        sources.postValue(sourcesRepo.fetchSources().map { SourcesItem(it) })
    }

    fun addToFavorites(sourcesItem: SourcesItem) = persistanceScope.launch {
        favoriteSourcesRepo.saveToFavorites(sourcesItem.source)
    }
}