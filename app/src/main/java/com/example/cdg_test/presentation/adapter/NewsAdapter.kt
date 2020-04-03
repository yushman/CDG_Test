package com.example.cdg_test.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cdg_test.R
import com.example.cdg_test.databinding.ItemNewsBinding
import com.example.cdg_test.model.item_model.NewsItem

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private lateinit var binding: ItemNewsBinding
    private var news = listOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    fun update(newNews: List<NewsItem>){
        val diffCallback = object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return news[oldItemPosition] == newNews[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return news.size
            }

            override fun getNewListSize(): Int {
                return newNews.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return news[oldItemPosition].hashCode() == newNews[newItemPosition].hashCode()
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) = Any()

        }
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        news = newNews
    }

    inner class NewsViewHolder(private val itemBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(newsItem: NewsItem){
            itemBinding.tvItemNewsTitle.text = newsItem.news.title
            itemBinding.tvItemNewsDescription.text = newsItem.news.description
            Glide.with(itemBinding.root).load(newsItem.news.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(itemBinding.ivItemNewsBgImage)
        }
    }
}