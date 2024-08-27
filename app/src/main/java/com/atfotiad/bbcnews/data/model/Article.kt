package com.atfotiad.bbcnews.data.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    var publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    var id: String
)