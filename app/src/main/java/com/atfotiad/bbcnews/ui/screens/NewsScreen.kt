package com.atfotiad.bbcnews.ui.screens

import android.content.Context
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atfotiad.bbcnews.BuildConfig
import com.atfotiad.bbcnews.LatestNewsUiState
import com.atfotiad.bbcnews.ui.common.NewsList
import com.atfotiad.bbcnews.NewsViewModel
import com.atfotiad.bbcnews.biometric.BiometricPrompt
import com.atfotiad.bbcnews.data.model.Article
import com.atfotiad.bbcnews.ui.common.ErrorScreen
import com.atfotiad.bbcnews.ui.common.MyToolbar
import com.atfotiad.bbcnews.ui.common.ShowLoadingIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel,
    onArticleClick: (Article) -> Unit = {}
) {

    Scaffold(topBar = { MyToolbar(sourceTitle = BuildConfig.SOURCES.replace("-", " ").uppercase(Locale.ROOT)) }) { paddingValues ->

        var isAuthenticated by rememberSaveable {
            mutableStateOf(false)
        }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        if (isBiometricAvailable(context)) {
            if (!isAuthenticated) {
                BiometricPrompt(
                    onSuccess = {
                        isAuthenticated = true
                    },
                    onError = { errorCode, errString ->
                        Log.d(
                            errorCode.toString(),
                            "NewsScreen: Authentication error:  $errorCode $errString"
                        )
                        if (errorCode == BiometricPrompt.ERROR_USER_CANCELED ||
                            errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON
                        ) {
                            (context as FragmentActivity).finish()
                        }
                    }
                )
            } else {
                val uiState by newsViewModel.uiState.collectAsStateWithLifecycle()
                when (uiState) {
                    is LatestNewsUiState.Loading -> ShowLoadingIndicator()
                    is LatestNewsUiState.Success -> NewsList(
                        modifier.padding(paddingValues),
                        list = ((uiState as LatestNewsUiState.Success).news)
                    ) { article ->
                        onArticleClick(article)
                    }

                    is LatestNewsUiState.Error -> ErrorScreen(
                        exception = (uiState as LatestNewsUiState.Error).exception,
                        onRetry = {
                            scope.launch(Dispatchers.IO) { newsViewModel.getNews() }
                        }
                    )
                }
            }
        } else {
            val uiState by newsViewModel.uiState.collectAsStateWithLifecycle()
            when (uiState) {
                is LatestNewsUiState.Loading -> ShowLoadingIndicator()
                is LatestNewsUiState.Success -> NewsList(
                    modifier.padding(paddingValues),
                    list = ((uiState as LatestNewsUiState.Success).news)
                ) { article ->
                    onArticleClick(article)
                }

                is LatestNewsUiState.Error -> ErrorScreen(
                    exception = (uiState as LatestNewsUiState.Error).exception,
                    onRetry = { scope.launch(Dispatchers.IO) { newsViewModel.getNews() } }
                )
            }
        }
    }
}

fun isBiometricAvailable(context: Context): Boolean {
    val biometricManager = BiometricManager.from(context)
    return biometricManager.canAuthenticate(
        BiometricManager.Authenticators
            .BIOMETRIC_STRONG or BiometricManager.Authenticators
            .DEVICE_CREDENTIAL
    ) == BiometricManager.BIOMETRIC_SUCCESS
}