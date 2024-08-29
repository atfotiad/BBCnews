package com.atfotiad.bbcnews

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.atfotiad.bbcnews.data.NewsRepository
import com.atfotiad.bbcnews.ui.screens.NewsScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ArticleListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    private lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        newsViewModel = NewsViewModel(repository)
    }

    @Test
    fun shouldDisplayArticles() {
        composeTestRule.setContent {
            NewsScreen(Modifier, newsViewModel) {

            }
        }
        composeTestRule.onNodeWithTag("list").isDisplayed()
        composeTestRule.onNodeWithTag("list").performScrollToIndex(4)
        composeTestRule.onNodeWithTag("list").onChildAt(4).performClick()


    }
}