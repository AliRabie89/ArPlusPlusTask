/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.remote.endPoints

import com.ali.task.main.data.models.Article
import com.ali.task.main.data.models.EndPointResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesEndPoints {
    @GET("everything")
    suspend fun getAllArticles(@Query("q") key: String,@Query("page") page: Int): Response<EndPointResult<List<Article>>>
}