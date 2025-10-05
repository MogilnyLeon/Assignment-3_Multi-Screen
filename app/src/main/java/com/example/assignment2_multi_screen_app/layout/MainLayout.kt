package com.example.assignment2_multi_screen_app.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.assignment2_multi_screen_app.LocalNavController

@Composable
fun MainLayout(
    screenTitle: String,
    content: @Composable () -> Unit
) {
    val navController = LocalNavController.current
    Scaffold (
        topBar = {SharedTopBar(screenTitle)},
        bottomBar = {SharedBottomBar()}
    ){ 
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}