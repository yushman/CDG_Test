package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cdg_test.R
import com.example.cdg_test.model.item_model.SourcesItem
import com.example.cdg_test.presentation.ViewModelFactory

class SourceNewsFragment(private val sourcesItem: SourcesItem) : Fragment() {

    companion object {
        fun newInstance(sourcesItem: SourcesItem) = SourceNewsFragment(sourcesItem)
    }

    private lateinit var viewModel: SourceNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.source_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(sourcesItem)).get(SourceNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
