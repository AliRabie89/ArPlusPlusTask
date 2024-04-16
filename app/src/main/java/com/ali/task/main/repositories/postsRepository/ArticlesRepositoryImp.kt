/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.repositories.postsRepository

import com.ali.task.main.common.extensions.mapToArticle
import com.ali.task.main.common.extensions.mapToArticleEntity
import com.ali.task.main.common.extensions.transformResponseData
import com.ali.task.main.data.local.ArticlesDAO
import com.ali.task.main.data.models.Article
import com.ali.task.main.data.remote.endPoints.ArticlesEndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImp @Inject constructor(
    private val articlesEndPoints : ArticlesEndPoints,
    private val articlesDAO: ArticlesDAO
) : ArticlesRepository {


    override  fun getAllArticles(key: String,page : Int) = flow {
        emit(articlesEndPoints.getAllArticles(key,page))
    }.transformResponseData { emit(it) }
        .flowOn(Dispatchers.IO)

    override suspend fun addToFavorite(article: Article) {
        articlesDAO.addToFavorite(article.mapToArticleEntity())
    }

    override suspend fun deleteFavorite(article: Article) {
        articlesDAO.deleteFavorite(article.mapToArticleEntity())
    }

    override suspend fun getAllArticlesFromFavorite() = flow {
        emit(articlesDAO.getAllFavorites().map {it.mapToArticle() })
    }.flowOn(Dispatchers.IO)
}