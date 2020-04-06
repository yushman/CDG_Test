package com.example.cdg_test.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cdg_test.constants.Constants
import com.example.cdg_test.databinding.NewsBySourceFragmentBinding
import com.example.cdg_test.model.item_model.FavoritesItem
import com.example.cdg_test.presentation.ViewModelFactory
import com.example.cdg_test.presentation.activity.MainActivity
import com.example.cdg_test.presentation.adapter.NewsAdapter

class NewsBySourceFragment : Fragment() {

    companion object {
        const val SOURCE_ID = "SOURCE_ID"
        const val SOURCE_NAME = "SOURCE_NAME"

        fun newInstance(favoritesItem: FavoritesItem): NewsBySourceFragment {
            val fragment = NewsBySourceFragment()
            val args = Bundle().apply {
                putString(SOURCE_ID, favoritesItem.source.id)
                putString(SOURCE_NAME, favoritesItem.source.name)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: NewsBySourceViewModel
    private lateinit var binding: NewsBySourceFragmentBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsBySourceFragmentBinding.inflate(inflater, container, false)
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
            val lm = LinearLayoutManager(this.context)
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lm.findLastVisibleItemPosition() > newsAdapter.itemCount - Constants.DEFAULT_FETCH_RESULT_SIZE / 10)
                        viewModel.fetchNewPage()
                }
            })
        }
    }

    private fun initVM(sourceId: String) {
        viewModel = ViewModelProvider(this, ViewModelFactory(sourceId)).get(NewsBySourceViewModel::class.java)
        viewModel.news.observe(this.viewLifecycleOwner, Observer { newsAdapter.update(it) })
        viewModel.fetchNews()
    }

}
