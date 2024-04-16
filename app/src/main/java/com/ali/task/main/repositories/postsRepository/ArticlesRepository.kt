/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.repositories.postsRepository

import com.ali.task.main.data.models.Article
import com.ali.task.main.data.models.EndPointResult
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getAllArticles(key: String,page : Int) : Flow<EndPointResult<List<Article>>>
    suspend fun addToFavorite(article: Article)
    suspend fun deleteFavorite(article: Article)
    suspend fun getAllArticlesFromFavorite() : Flow<List<Article>>
}