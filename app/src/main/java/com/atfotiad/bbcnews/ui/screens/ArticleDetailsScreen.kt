package com.atfotiad.bbcnews.ui.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.atfotiad.bbcnews.data.model.Article
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    article: Article
) {
    ConstraintLayout(Modifier.verticalScroll(rememberScrollState(), true)) {
        val screenContent = createRef()
        LaunchedEffect(key1 = Unit) {
            Log.d("Article", "Constraint Layout: ${article.title}")
        }
        SingleArticle(article, modifier.constrainAs(screenContent) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingleArticle(article: Article, modifier: Modifier = Modifier) {
    Card(
        modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(30.dp),
    ) {

        Column(modifier.padding(16.dp)) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = "Article image",
                modifier = modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onSurface, RectangleShape)
                    .padding(8.dp)
            )

            LaunchedEffect(key1 = Unit) {
                Log.d("Title", "SingleArticle: ${article.title}")
            }
            Text(
                text = article.title,
                modifier.padding(top = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(text = article.author, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = article.description, fontWeight = FontWeight.Normal, fontSize = 14.sp)
            Text(text = article.content, fontWeight = FontWeight.Normal, fontSize = 12.sp)
        }
    }
}