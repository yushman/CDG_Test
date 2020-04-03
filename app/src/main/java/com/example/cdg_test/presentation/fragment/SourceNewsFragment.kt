package com.example.cdg_test.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cdg_test.databinding.SourceNewsFragmentBinding
import com.example.cdg_test.model.item_model.FavoritesItem
import com.example.cdg_test.presentation.ViewModelFactory
import com.example.cdg_test.presentation.activity.MainActivity
import com.example.cdg_test.presentation.adapter.NewsAdapter

class SourceNewsFragment : Fragment() {

    companion object {
        const val SOURCE_ID = "SOURCE_ID"
        const val SOURCE_NAME = "SOURCE_NAME"

        fun newInstance(favoritesItem: FavoritesItem): SourceNewsFragment {
            val fragment = SourceNewsFragment()
            val args = Bundle().apply {
                putString(SOURCE_ID, favoritesItem.source.id)
                putString(SOURCE_NAME, favoritesItem.source.name)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SourceNewsViewModel
    private lateinit var binding: SourceNewsFragmentBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SourceNewsFragmentBinding.inflate(inflater, container, false)
        arguments?.getString(SOURCE_ID)?.let { initVM(it) }
        arguments?.getString(SOURCE_NAME)?.let{ initViews(it) }
        return binding.root
    }

    private fun initViews(sourceName: String) {
        val activity = activity as MainActivity
        activity.title = "News for $sourceName"
        newsAdapter = NewsAdapter()
        binding.rvSourceNewsFragment.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initVM(sourceId: String) {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(sourceId)).get(SourceNewsViewModel::class.java)
        viewModel.news.observe(this.viewLifecycleOwner, Observer { newsAdapter.update(it) })
        viewModel.fetchNews()
    }

}
