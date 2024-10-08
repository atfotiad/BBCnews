package com.atfotiad.bbcnews.data

import com.atfotiad.bbcnews.BuildConfig
import com.atfotiad.bbcnews.api.NewsClient
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsClient: NewsClient
) {

    private val sourceString = BuildConfig.SOURCES
    suspend fun getNews() = newsClient.getNews(sourceString)


}