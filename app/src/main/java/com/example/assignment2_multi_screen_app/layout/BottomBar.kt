package com.example.assignment2_multi_screen_app.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.assignment2_multi_screen_app.LocalNavController
import com.example.assignment2_multi_screen_app.routes.Routes

/**
 * A shared bottom navigation bar used for navigating between app screens.
 *
 * This composable displays a [BottomAppBar] with two action buttons:
 * - A **Create** button that navigates to the content creation screen.
 * - A **List** button that navigates to the content list screen.
 *
 * It retrieves the current [androidx.navigation.NavController] instance from [LocalNavController]
 * and uses it to handle navigation when icons are clicked.
 *
 * Example usage:
 * ```
 * Scaffold(
 *     bottomBar = { SharedBottomBar() }
 * ) { innerPadding ->
 *     // Screen content here
 * }
 * ```
 *
 * @see BottomAppBar
 * @see IconButton
 * @see LocalNavController
 */
@Composable
fun SharedBottomBar() {
    val navController = LocalNavController.current

    BottomAppBar (
        actions = {
            IconButton({navController.navigate(Routes.Creation.route)}) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "Create Content"
                )
            }
            IconButton({navController.navigate(Routes.DisplayList.route)}) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "Content List"
                )
            }
        }
    )
}