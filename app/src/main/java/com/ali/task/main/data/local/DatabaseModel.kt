/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.local

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseModel : RoomDatabase() {
    abstract fun articlesDAO(): ArticlesDAO
}