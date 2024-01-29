/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.remote.endPoints

import com.ali.task.main.data.models.PostModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsEndPoints {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostModel>>
    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id : Int): Response<PostModel>
}