package com.atfotiad.bbcnews.ui.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolbar(sourceTitle: String) {
    TopAppBar(title = { Text(text = sourceTitle) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan),
        windowInsets = WindowInsets(top = 0.dp)
    )
}