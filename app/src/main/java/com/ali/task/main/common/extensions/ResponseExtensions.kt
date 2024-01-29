/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.common.extensions

import android.util.Log
import com.ali.task.main.common.Constants
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.transform
import retrofit2.Response

val TAG = "ResponseExtensions"
inline fun <T> Flow<Response<T>>.transformResponseData(
    crossinline onSuccess: suspend FlowCollector<T>.(T) -> Unit,
): Flow<T> {
    return transform {
        val errorBody = it.errorBody()
        val body = it.body()


        if (it.isSuccessful && body != null) {
            onSuccess(body)
            return@transform
        }
        errorBody?.let { errorBody ->
            when (it.code()) {
                Constants.HttpRequestErrorCode.SERVER_ERROR -> {
                    throw Throwable("Internal Server Error")
                }

                Constants.HttpRequestErrorCode.UN_AUTHORIZED -> {
                    throw Throwable("Not Authorized")
                }

                Constants.HttpRequestErrorCode.PERMISSION_DENIED -> {
                    throw Throwable("Denied")
                }

                Constants.HttpRequestErrorCode.NOT_FOUND -> {
                    throw Throwable(Gson().toJson("Not Found"))
                }

                Constants.HttpRequestErrorCode.INVALID_INPUT -> {
                    throw Throwable("Invalid Input")
                }

                else -> {
                    Log.e(TAG, it.code().toString() + "," + errorBody.toString())
                    throw Throwable(errorBody.string())
                }
            }
        }
    }
}
