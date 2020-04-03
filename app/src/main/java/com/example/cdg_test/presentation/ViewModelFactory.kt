package com.example.cdg_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cdg_test.presentation.fragment.SourceNewsViewModel

class ViewModelFactory (private val sourceId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  SourceNewsViewModel(sourceId) as T
    }
}