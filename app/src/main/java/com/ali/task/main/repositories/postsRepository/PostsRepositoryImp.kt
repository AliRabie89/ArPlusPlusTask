/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.repositories.postsRepository

import com.ali.task.main.common.extensions.transformResponseData
import com.ali.task.main.data.models.PostModel
import com.ali.task.main.data.preference.SharedPreferencesKeys
import com.ali.task.main.data.preference.SharedPreferencesManager
import com.ali.task.main.data.remote.endPoints.PostsEndPoints
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val postsEndPoints : PostsEndPoints,
) : PostsRepository {


    override fun getAllPosts(): Flow<List<PostModel>> = flow {
        emit(postsEndPoints.getAllPosts())
    }.transformResponseData { emit(it) }


    override fun getPostById(id: Int): Flow<PostModel> = flow {
        emit(postsEndPoints.getPostById(id))
    }.transformResponseData { emit(it) }

}