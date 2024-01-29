/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.posts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ali.task.R
import com.ali.task.databinding.ItemLayoutPostBinding
import com.ali.task.main.base.CustomBaseAdapter
import com.ali.task.main.data.models.PostModel

class PostsAdapter(var onItemClicked:(postModel:PostModel)->Unit) : CustomBaseAdapter<PostModel, PostsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout and create ViewHolder instance
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutPostBinding.bind(itemView)
        fun bind(item: PostModel) {
            binding.post = item
            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }

    }
}