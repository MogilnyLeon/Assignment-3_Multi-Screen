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

/**
 * Sets up the application's navigation graph and provides a shared [NavController].
 *
 * This composable defines the root navigation structure for the app using [NavHost] and
 * [rememberNavController]. It provides the [LocalNavController] composition local to
 * descendant composables, allowing access to the shared [NavController] throughout the app.
 *
 * The navigation graph includes:
 * - [Routes.Creation] — Displays the [ContentCreationScreen].
 * - [Routes.DisplayList] — Displays the [DisplayListScreen].
 * - [Routes.DisplayDetails] — Displays the [DisplayDetailsScreen] for a specific content item.
 *
 * A shared [ImageContentViewModel] is scoped to the "RootRoute" back stack entry,
 * ensuring that all screens within this root navigation share the same ViewModel instance.
 *
 * Each composable destination extracts its `backstackEntry` arguments as needed, e.g.,
 * the `name` parameter for [Routes.DisplayDetails].
 *
 * Example usage:
 * ```
 * @Composable
 * fun MyApp() {
 *     Router()
 * }
 * ```
 *
 * @see NavHost
 * @see rememberNavController
 * @see CompositionLocalProvider
 * @see LocalNavController
 * @see ImageContentViewModel
 * @see Routes
 */
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
