package com.atfotiad.bbcnews

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
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
class ScreenTitleTest {

    private lateinit var newsViewModel: NewsViewModel
   @Inject lateinit var repository: NewsRepository

   @Before
    fun setUp() {
        newsViewModel = NewsViewModel(repository)
    }

    @get:Rule
    val composeTestRule = createComposeRule()
    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    @Test
    fun addState_Assert_ScreenTitle_IsDisplayed() {
        composeTestRule.setContent {
            NewsScreen(Modifier,newsViewModel) {

            }
        }
        composeTestRule.onNodeWithText("BBC News").assertIsDisplayed()
        composeTestRule.onNodeWithTag("toolbar").assertIsDisplayed()

    }
}