/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostModel (
    @SerializedName("id")
    @Expose
    var id : Int,
    @SerializedName("title")
    @Expose
    var title : String,
    @SerializedName("body")
    @Expose
    var body : String
)