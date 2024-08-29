package com.atfotiad.bbcnews

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.atfotiad.bbcnews.data.NewsRepository
import com.atfotiad.bbcnews.navigation.NewsNavHost
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    private lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var repository: NewsRepository


    lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        newsViewModel = NewsViewModel(repository)
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NewsNavHost(navController = navController, viewModel = newsViewModel)
        }
    }

    @Test
    fun testNavigation() {
        composeTestRule.onNodeWithTag("list").onChildAt(2).assertHasClickAction().performClick()

    }
}