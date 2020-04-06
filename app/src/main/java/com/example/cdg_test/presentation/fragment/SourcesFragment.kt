package com.example.cdg_test.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cdg_test.databinding.SourcesFragmentBinding
import com.example.cdg_test.model.item_model.SourcesItem
import com.example.cdg_test.presentation.adapter.SourcesAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class SourcesFragment : Fragment() {

    companion object {
        fun newInstance() = SourcesFragment()
    }

    private val viewModel by inject<SourcesViewModel>()
    private lateinit var binding: SourcesFragmentBinding
    private lateinit var sourcesAdapter: SourcesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SourcesFragmentBinding.inflate(inflater, container, false)
        initVM()
        initViews()
        return binding.root
    }

    private fun initViews() {
        sourcesAdapter = SourcesAdapter{ addToFavoriteClick(it)}
        binding.rvSourcesFragment.apply {
            adapter = sourcesAdapter
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initVM() {
        viewModel.sources.observe(this.viewLifecycleOwner, Observer { sourcesAdapter.update(it) })
        viewModel.fetchSources()
    }

    private fun addToFavoriteClick(sourcesItem: SourcesItem) {
        viewModel.addTofavorites(sourcesItem)
        Snackbar.make(binding.root, sourcesItem.source.name + " was added to favorites", Snackbar.LENGTH_LONG).show()
        sourcesAdapter.updateItem(sourcesItem)
    }

}
