/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.models
sealed class NetworkState {
    object Loading : NetworkState()
    object StopLoading : NetworkState()
    data class Success<T>(val data: T) : NetworkState()
    data class Error(val throwable: Throwable) : NetworkState()
}