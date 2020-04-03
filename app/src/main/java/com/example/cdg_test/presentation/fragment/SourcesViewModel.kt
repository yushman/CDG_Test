package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModel
import com.example.cdg_test.manager.SourcesManager
import com.example.cdg_test.model.item_model.SourcesItem

class SourcesViewModel : ViewModel() {
    private val sourcesManager = SourcesManager()
    val sources = sourcesManager.sources

    fun fetchSources() = sourcesManager.fetchSources()

    fun addTofavorites(sourcesItem: SourcesItem) = sourcesManager.addToFavorites(sourcesItem)
}
