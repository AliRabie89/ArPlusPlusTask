/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.common.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.ali.task.R
import com.ali.task.main.data.local.ArticleEntity
import com.ali.task.main.data.models.Article
import com.google.gson.Gson

/**Print log*/
fun Any.showVLog(log: String, tag: String = getClassName()) = Log.v(tag, log)

fun Any.showELog(log: String, tag: String = getClassName()) = Log.e(tag, log)

fun Any.showDLog(log: String, tag: String = getClassName()) = Log.d(tag, log)

fun Any.showILog(log: String, tag: String = getClassName()) = Log.i(tag, log)

fun Any.showWLog(log: String, tag: String = getClassName()) = Log.w(tag, log)

fun Any.toJson(any: Any)= Gson().toJson(any)!!

inline fun <reified T> String.parseJson(): T {
    val gson = Gson()
    return gson.fromJson(this, T::class.java)
}

fun Context.openUrl(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Check if there's an app that can handle this intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // No application can handle the URL
            Toast.makeText(this,
                getString(R.string.no_application_found_to_open_this_link), Toast.LENGTH_LONG).show()
        }
    } catch (e: Exception) {
        Toast.makeText(this,
            getString(R.string.error_opening_link, e.localizedMessage), Toast.LENGTH_LONG).show()
    }
}

fun Article.mapToArticleEntity():ArticleEntity{
    val articleEntity = ArticleEntity()
    articleEntity.title = title!!
    articleEntity.author = author
    articleEntity.description = description
    articleEntity.publishedAt = publishedAt
    articleEntity.url = url
    articleEntity.urlToImage = urlToImage
    return articleEntity
}

fun ArticleEntity.mapToArticle():Article{
    val article = Article()
    article.title = title
    article.author = author
    article.description = description
    article.publishedAt = publishedAt
    article.url = url
    article.urlToImage = urlToImage
    return article
}