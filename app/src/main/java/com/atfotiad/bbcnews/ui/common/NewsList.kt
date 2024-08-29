package com.atfotiad.bbcnews.ui.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.atfotiad.bbcnews.data.model.Article

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    list: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier.testTag("list")
    ) {
        items(count = list.size) { index ->
            val article = list[index]
            ArticleItem(article = article, onArticleClick = onClick)
            HorizontalDivider()
        }
    }
}