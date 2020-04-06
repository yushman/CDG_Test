package com.example.cdg_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cdg_test.presentation.fragment.NewsBySourceViewModel

class ViewModelFactory (private val sourceId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  NewsBySourceViewModel(sourceId) as T
    }
}