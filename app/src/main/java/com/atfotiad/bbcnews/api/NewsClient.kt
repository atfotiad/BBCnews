package com.atfotiad.bbcnews.api

import com.atfotiad.bbcnews.BuildConfig
import com.atfotiad.bbcnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsClient {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") sourceProvider: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponse>


}