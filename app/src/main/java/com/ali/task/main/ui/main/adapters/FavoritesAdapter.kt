/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.task.R
import com.ali.task.databinding.ItemLayoutArticleBinding
import com.ali.task.databinding.ItemLayoutFavoriteBinding
import com.ali.task.main.base.CustomBaseAdapter
import com.ali.task.main.base.CustomLoadOnScrollAdapterCustom
import com.ali.task.main.data.models.Article

class FavoritesAdapter(var onItemUrlClicked:(url : String)->Unit, var onRemoveFromFavoriteClicked:(article : Article)->Unit) : CustomLoadOnScrollAdapterCustom<Article, FavoritesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout and create ViewHolder instance
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutFavoriteBinding.bind(itemView)
        fun bind(item: Article) {
            binding.article = item
            binding.removeFavBtn.setOnClickListener { onRemoveFromFavoriteClicked(item) }
            binding.urlBtn.setOnClickListener { onItemUrlClicked(item.url!!) }
        }

    }
}