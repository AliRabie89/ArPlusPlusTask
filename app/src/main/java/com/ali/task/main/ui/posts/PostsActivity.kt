/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.posts

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.ali.task.databinding.PostsActivityBinding
import com.ali.task.main.base.BaseActivity
import com.ali.task.main.common.extensions.handleResultSharedFlow
import com.ali.task.main.data.models.PostModel
import com.ali.task.main.ui.posts.adapters.PostsAdapter
import com.ali.task.main.ui.posts.viewModels.PostsViewModel

class PostsActivity : BaseActivity() {

    lateinit var binding : PostsActivityBinding
    private val postsViewModel : PostsViewModel by viewModels()
    lateinit var postsAdapter: PostsAdapter

    override fun setUpLayoutView(): View {
        binding = PostsActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPostsListUI()
        initPostsData()
    }

    private fun initPostsData() {
        handleResultSharedFlow(postsViewModel.postDetailsFlow, onSuccess = {
            if(it is List<*>){
                postsAdapter.addItems(it as List<PostModel>)
            }
        }, onShowProgress = {
            binding.postsList.showShimmer()
        }, onHideProgress = {
            binding.postsList.hideShimmer()
            binding.refresher.isRefreshing = false
        })

        postsViewModel.callAllPosts()
    }

    private fun initPostsListUI() {
        postsAdapter = PostsAdapter {
            //Here fire new activity and send post ID to get post details
        }
        binding.postsList.apply {
            adapter = postsAdapter
        }

        binding.refresher.setOnRefreshListener { postsViewModel.callAllPosts() }
    }
}