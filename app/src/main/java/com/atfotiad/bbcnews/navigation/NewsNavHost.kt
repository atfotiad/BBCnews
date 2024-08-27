package com.atfotiad.bbcnews.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.atfotiad.bbcnews.navigation.ArticleDetailsScreen
import com.atfotiad.bbcnews.LatestNewsUiState
import com.atfotiad.bbcnews.ui.screens.NewsScreen
import com.atfotiad.bbcnews.NewsViewModel
import com.atfotiad.bbcnews.data.model.Article

@Composable
fun NewsNavHost(
    navController: NavHostController,
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NewsListScreen.route,
        modifier
    ) {
        composable(route = NewsListScreen.route) {
            NewsScreen(newsViewModel = viewModel) { article ->
                navController.navigateToSingleArticle(article)
            }
        }

        composable(
            route = ArticleDetailsScreen.routeWithArgs,
            arguments = ArticleDetailsScreen.arguments
        ) { navBackStackEntry ->
            val articleArgument =
                navBackStackEntry.arguments?.getString(ArticleDetailsScreen.ARTICLE_ARG)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val articleFromViewModel =
                (uiState as LatestNewsUiState.Success).news.onEach { article: Article ->
                    Log.d("Titles", "NewsNavHost: ${article.id}")
                    Log.d("argument", "NewsNavHost: $articleArgument")
                }.find { it.id == articleArgument }
            if (articleFromViewModel != null) {
                ArticleDetailsScreen(article = articleFromViewModel)
            } else {
                Log.d("Title", "NewsNavHost: Arg is Null")
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.navigateToSingleArticle(article: Article) {
    Log.i("toSingle", "navigateToSingleArticle with Article: ${article.id}")
    this.navigateSingleTopTo("${ArticleDetailsScreen.route}/${article.id}")
}