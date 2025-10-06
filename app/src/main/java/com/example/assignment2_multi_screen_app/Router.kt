package com.example.assignment2_multi_screen_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2_multi_screen_app.routes.Routes
import com.example.assignment2_multi_screen_app.screens.ContentCreationScreen
import com.example.assignment2_multi_screen_app.screens.DisplayListScreen

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }


@Composable
fun Router() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = Routes.Creation.route
        ) {
            composable(Routes.DisplayList.route) {
                DisplayListScreen()
            }
            composable(Routes.DisplayDetails.route) {
                // DisplayDetailsScreen(name = it.arguments?.getString("name") ?: "")
            }
            composable(Routes.Creation.route) {
                ContentCreationScreen()
            }
        }
    }
}
