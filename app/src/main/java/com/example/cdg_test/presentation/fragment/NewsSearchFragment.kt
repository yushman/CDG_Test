package com.example.cdg_test.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cdg_test.constants.Constants
import com.example.cdg_test.databinding.NewsSearchFragmentBinding
import com.example.cdg_test.presentation.adapter.NewsAdapter
import org.koin.android.ext.android.inject

class NewsSearchFragment : Fragment() {

    companion object {
        fun newInstance() = NewsSearchFragment()
    }

    private val viewModel by inject<NewsSearchViewModel>()
    private lateinit var binding: NewsSearchFragmentBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsSearchFragmentBinding.inflate(inflater, container, false)
        initVM()
        initViews()
        return binding.root
    }

    private fun initViews() {
        newsAdapter = NewsAdapter()
        binding.rvNewsSearchFragment.apply {
            adapter = newsAdapter
            val lm = LinearLayoutManager(this.context)
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (lm.findLastVisibleItemPosition() > newsAdapter.itemCount - Constants.DEFAULT_FETCH_RESULT_SIZE / 10)
                        viewModel.fetchNextPage()
                }
            })
        }
        binding.svNewSearchFragment.apply {
            setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.proceedNewSearch(it)
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
            isIconified = false
            focusable = SearchView.FOCUSABLE
            requestFocus()
            requestFocusFromTouch()
            setQuery("", false)

        }
    }

    private fun initVM() {
        viewModel.news.observe(this.viewLifecycleOwner, Observer { newsAdapter.update(it) })
    }
}
