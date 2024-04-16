/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.ui.main.viewModels

import androidx.lifecycle.viewModelScope
import com.ali.task.main.base.BaseViewModel
import com.ali.task.main.data.models.Article
import com.ali.task.main.data.models.NetworkState
import com.ali.task.main.repositories.postsRepository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private var articlesRepository : ArticlesRepository) : BaseViewModel() {
    private val _articles = MutableSharedFlow<NetworkState>()
    val articlesFlow get() = _articles

    private val _favorites = MutableSharedFlow<NetworkState>()
    val favoritesFlow get() = _favorites

    fun callArticles(key: String,page : Int){
        viewModelScope.launch {
            val articlesRequest = articlesRepository.getAllArticles(key,page)
            executeSharedFlow(articlesFlow,articlesRequest)
        }
    }

    fun addArticleToFavorite(article: Article){
        viewModelScope.launch {
            articlesRepository.addToFavorite(article)
            delay(300)
            callArticlesFromFavorites()
        }
    }

    fun deleteFavorite(article: Article){
        viewModelScope.launch {
            articlesRepository.deleteFavorite(article)
            delay(300)
            callArticlesFromFavorites()
        }
    }

    fun callArticlesFromFavorites(){
        viewModelScope.launch {
            val articlesRequest = articlesRepository.getAllArticlesFromFavorite()
            executeSharedFlow(favoritesFlow,articlesRequest)
        }
    }
}

