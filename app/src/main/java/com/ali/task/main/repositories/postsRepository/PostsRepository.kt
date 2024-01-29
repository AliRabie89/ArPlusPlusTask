/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.repositories.postsRepository

import com.ali.task.main.data.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts() : Flow<List<PostModel>>
    fun getPostById(id : Int) : Flow<PostModel>
}