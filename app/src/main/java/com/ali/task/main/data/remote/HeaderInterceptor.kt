/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    val ANDROID = "0"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return addRequestHeaders(chain)
    }

    private fun addRequestHeaders(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val originalHttpUrl = origin.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("culture", "")
            .addQueryParameter("ui-culture", "")
            .build()
        var request: Request? = null
        var builder: Request.Builder? = null
        builder = chain.request().newBuilder()
            .url(url)
            .addHeader("Accept", "application/json")
        try {
            request = builder.build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return chain.proceed(request!!)
    }


    private fun internalServerError(response: Response): Boolean {
        //500 internal server error
        return (response.code == 500)
    }

    private fun shouldLogout(response: Response): Boolean {
        // 401 and auth token means that we need to logout
        return (response.code == 401)
    }
}