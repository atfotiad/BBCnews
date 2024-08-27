package com.atfotiad.bbcnews.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atfotiad.bbcnews.data.model.Article
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(
    article: Article,
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier
            .padding(8.dp)
            .clickable { onArticleClick(article) },
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = "Article image",
                modifier = modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .padding(8.dp)
            )
            Text(
                modifier = modifier.padding(start = 8.dp),
                text = article.title,
                textAlign = TextAlign.Start
            )
            Text(
                modifier = modifier.padding(start = 8.dp),
                text = "Published at: " + article.publishedAt,
                textAlign = TextAlign.Start
            )

        }
    }
}