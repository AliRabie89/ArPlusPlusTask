/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDAO {
    @Query("SELECT * FROM FAVORITES")
    suspend fun getAllFavorites(): List<ArticleEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(article: ArticleEntity)
    @Delete
    suspend fun deleteFavorite(article: ArticleEntity)
}