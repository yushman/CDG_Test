package com.example.cdg_test.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cdg_test.R
import com.example.cdg_test.databinding.ItemSourcesBinding
import com.example.cdg_test.model.item_model.SourcesItem

class SourcesAdapter(private val l:(SourcesItem) -> Unit) : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {
    private lateinit var binding: ItemSourcesBinding
    private var sources = listOf<SourcesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesAdapter.SourcesViewHolder {
        binding = ItemSourcesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourcesViewHolder(binding)
    }

    override fun getItemCount() = sources.size

    override fun onBindViewHolder(holder: SourcesAdapter.SourcesViewHolder, position: Int) {
        holder.bind(sources[position])
    }

    fun update(newSources: List<SourcesItem>) {
        val diffCallback = object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return sources[oldItemPosition] == newSources[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return sources.size
            }

            override fun getNewListSize(): Int {
                return newSources.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return sources[oldItemPosition].hashCode() == newSources[newItemPosition].hashCode()
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) = Any()

        }
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        sources = newSources
    }

    fun updateItem(sourcesItem: SourcesItem) {
        val newSources = sources.toMutableList()
        val index = sources.indexOf(sourcesItem)
        newSources[index] = sourcesItem.copy(isInFavorites = true)
        update(newSources)
    }

    inner class SourcesViewHolder(private val itemBinding: ItemSourcesBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(sourcesItem: SourcesItem){
            itemBinding.tvItemSourcesName.text = sourcesItem.source.name
            itemBinding.tvItemSourcesDescr.text = sourcesItem.source.description
            itemBinding.btnItemSourcesAddToFavorites.apply {
                setOnClickListener { l.invoke(sourcesItem) }
                if (sourcesItem.isInFavorites) {
                    text = context.getString(R.string.in_favorites)
                    setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_check_gray_24dp, 0, 0, 0)
                } else {
                    text = context.getString(R.string.add_to_favorites)
                    setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_create_new_folder_black_24dp, 0, 0, 0)
                }
            }

        }
    }
}