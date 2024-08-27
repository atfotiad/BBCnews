package com.atfotiad.bbcnews.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NewsAppDestination {
    val route: String
}

object NewsListScreen: NewsAppDestination {
    override val route: String = "newsList"
}

object ArticleDetailsScreen: NewsAppDestination {
    override val route: String = "articleDetails"
    const val ARTICLE_ARG = "ArticleArg"
    val routeWithArgs = "$route/{$ARTICLE_ARG}"
    val arguments = listOf(navArgument(ARTICLE_ARG) { type = NavType.StringType })
}