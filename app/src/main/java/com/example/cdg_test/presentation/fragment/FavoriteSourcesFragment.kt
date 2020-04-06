package com.example.cdg_test.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cdg_test.R
import com.example.cdg_test.databinding.FavoriteSourcesFragmentBinding
import com.example.cdg_test.model.item_model.FavoritesItem
import com.example.cdg_test.presentation.adapter.FavoritesAdapter
import com.example.cdg_test.presentation.adapter.ItemTouchHelperCallback
import org.koin.android.ext.android.inject

class FavoriteSourcesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteSourcesFragment()
    }

    private val viewModel by inject<FavoriteSourcesViewModel>()
    private lateinit var binding: FavoriteSourcesFragmentBinding
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteSourcesFragmentBinding.inflate(inflater, container, false)
        initVM()
        initViews()
        return binding.root
    }

    private fun initViews() {
        favoritesAdapter = FavoritesAdapter { listNewsClick(it) }
        binding.rvFavoritesFragment.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        }
        val touchCallback =
            ItemTouchHelperCallback {
                removeFromFavorites(favoritesAdapter.getItem(it))
            }
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(binding.rvFavoritesFragment)

    }

    private fun initVM() {
        viewModel.favorites.observe(this.viewLifecycleOwner, Observer { favoritesAdapter.update(it) })
    }

    private fun removeFromFavorites(favoritesItem: FavoritesItem) {
        viewModel.removeFromFavorites(favoritesItem)
    }

    private fun listNewsClick(favoritesItem: FavoritesItem) {
        val newsFragment = NewsBySourceFragment.newInstance(favoritesItem)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, newsFragment)
            .addToBackStack(null)
            .commit()
    }

}
