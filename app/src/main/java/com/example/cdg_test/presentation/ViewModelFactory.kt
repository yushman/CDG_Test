package com.example.cdg_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cdg_test.model.item_model.SourcesItem
import com.example.cdg_test.presentation.fragment.SourceNewsViewModel

class ViewModelFactory (private val sourcesItem: SourcesItem): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  SourceNewsViewModel(sourcesItem) as T
    }
}