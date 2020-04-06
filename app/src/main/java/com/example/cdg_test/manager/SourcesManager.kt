package com.example.cdg_test.manager

import androidx.lifecycle.MutableLiveData
import com.example.cdg_test.model.dto_model.SourcesDto
import com.example.cdg_test.model.item_model.SourcesItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class SourcesManager: AbstractManager() {
    val sources: MutableLiveData<List<SourcesItem>> = MutableLiveData()

    fun fetchSources() = networkingScope.launch {
        supervisorScope {
            try {
                sourcesRepo.fetchSources()?.let { list ->
                    sources.postValue(list.map { toSourceItem(it) })
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun addToFavorites(sourcesItem: SourcesItem) = persistanceScope.launch {
        favoriteSourcesRepo.saveToFavorites(sourcesItem.source)
    }

    private fun toSourceItem(dto: SourcesDto): SourcesItem {
        var isInFavorites = false
        favoriteSourcesRepo.getFromFavoritesById(dto.id)?.let { isInFavorites = true }
        return SourcesItem(dto, isInFavorites)
    }
}