package com.example.cdg_test.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cdg_test.databinding.ItemFavoritesBinding
import com.example.cdg_test.model.item_model.FavoritesItem

class FavoritesAdapter(private val l:(FavoritesItem) -> Unit) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){
    private lateinit var binding: ItemFavoritesBinding
    private var favorites = listOf<FavoritesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun update(newFavorites: List<FavoritesItem>) {
        val diffCallback = object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return favorites[oldItemPosition] == newFavorites[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return favorites.size
            }

            override fun getNewListSize(): Int {
                return newFavorites.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return favorites[oldItemPosition].hashCode() == newFavorites[newItemPosition].hashCode()
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) = Any()

        }
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        favorites = newFavorites
    }

    fun getItem(position: Int) = favorites[position]

    inner class FavoritesViewHolder(private val itemBinding: ItemFavoritesBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(favoritesItem: FavoritesItem){
            itemBinding.tvItemFavoritesName.text = favoritesItem.source.name
            itemBinding.tvItemFavoritesDescr.text = favoritesItem.source.description
            itemBinding.btnItemFavoritesListNews.setOnClickListener { l.invoke(favoritesItem) }
        }
    }
}