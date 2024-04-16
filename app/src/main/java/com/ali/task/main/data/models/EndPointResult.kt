/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.models

import com.google.gson.annotations.SerializedName

class EndPointResult<T>(
    @SerializedName("status")
    val status : String,
    @SerializedName("totalResults")
    val totalResults : Int,
    @SerializedName("articles")
    val articles : T
)