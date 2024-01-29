/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.posts.viewModels

import androidx.lifecycle.viewModelScope
import com.ali.task.main.base.BaseViewModel
import com.ali.task.main.data.models.NetworkState
import com.ali.task.main.repositories.postsRepository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private var postsRepository: PostsRepository) : BaseViewModel() {
    private val _posts = MutableSharedFlow<NetworkState>()
    val postsFlow get() = _posts
    private val _postDetails = MutableSharedFlow<NetworkState>()
    val postDetailsFlow get() = _posts

    fun callAllPosts(){
        viewModelScope.launch {
            val postsRequest = postsRepository.getAllPosts()
            executeSharedFlow(postsFlow,postsRequest)
        }
    }

    fun callPostById(id : Int){
        viewModelScope.launch {
            val postsDetailsRequest = postsRepository.getPostById(id)
            executeSharedFlow(postDetailsFlow,postsDetailsRequest)
        }
    }

}

