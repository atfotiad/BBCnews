package com.atfotiad.bbcnews

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.atfotiad.bbcnews.navigation.NewsNavHost
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    companion object {

        @Composable
        fun NewsApp() {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                val viewModel: NewsViewModel = hiltViewModel()
                Scaffold(
                    modifier = Modifier,
                ) { innerPadding ->
                    NewsNavHost(
                        navController = navController, viewModel, Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
}