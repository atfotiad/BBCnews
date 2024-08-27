package com.atfotiad.bbcnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atfotiad.bbcnews.data.NewsRepository
import com.atfotiad.bbcnews.data.helpers.convertUtcTimestampToDate
import com.atfotiad.bbcnews.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.Loading)
    val uiState: StateFlow<LatestNewsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNews().collect { news ->
                if (news.isNotEmpty()) {
                    _uiState.value = LatestNewsUiState.Success(news)
                }
            }
        }
    }


    suspend fun getNews(): Flow<List<Article>> {
        var articles: List<Article> = emptyList()
        _uiState.value = LatestNewsUiState.Loading

        try {
            articles = repository.getNews().body()?.articles ?: emptyList()
            if (articles.isNotEmpty()) {
                _uiState.value = LatestNewsUiState.Success(articles)
            }
        } catch (throwable: Throwable) {
            _uiState.value = LatestNewsUiState.Error(throwable)
        }

        articles.forEach { article ->
            article.publishedAt = convertUtcTimestampToDate(article.publishedAt).toString()
            Log.d("Title from viewModel", "getNews: ${article.title}")
        }

        //create id for routing purposes because
        // titles containing special chars
        // like "?" are problematic
        repeat(articles.size) { index ->
            articles[index].id = index.inc().toString()
        }
        return flowOf(articles)
    }
}

sealed interface LatestNewsUiState {
    data object Loading : LatestNewsUiState
    data class Success(val news: List<Article>) : LatestNewsUiState
    data class Error(val exception: Throwable) : LatestNewsUiState
}