package com.example.assignment2_multi_screen_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.routes.Routes
import com.example.assignment2_multi_screen_app.screens.ContentCreationScreen
import com.example.assignment2_multi_screen_app.screens.DisplayDetailsScreen
import com.example.assignment2_multi_screen_app.screens.DisplayListScreen

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }


@Composable
fun Router() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = "RootRoute"
        ) {
            navigation(
                startDestination = Routes.Creation.route,
                route = "RootRoute"
            ) {
                composable(Routes.DisplayList.route) { backstackEntry ->
                    val parent = remember(backstackEntry) {
                        navController.getBackStackEntry("RootRoute")
                    }
                    val sharedViewModel: ImageContentViewModel = viewModel(parent)
                    DisplayListScreen(contentViewModel = sharedViewModel)
                }

                composable(Routes.Creation.route) { backstackEntry ->
                    val parent = remember(backstackEntry) {
                        navController.getBackStackEntry("RootRoute")
                    }
                    val sharedViewModel: ImageContentViewModel = viewModel(parent)
                    ContentCreationScreen(sharedViewModel)
                }

                composable(Routes.DisplayDetails.route) { backstackEntry ->
                    val parent = remember(backstackEntry) {
                        navController.getBackStackEntry("RootRoute")
                    }
                    val sharedViewModel: ImageContentViewModel = viewModel(parent)
                    val name = backstackEntry.arguments?.getString("name") ?: ""
                    DisplayDetailsScreen(name, sharedViewModel)
                }
            }

        }
    }
}
