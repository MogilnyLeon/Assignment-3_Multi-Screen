package com.example.assignment2_multi_screen_app.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.assignment2_multi_screen_app.LocalNavController

/**
 * A reusable top app bar with a centered title and a back navigation button.
 *
 * This composable displays a [CenterAlignedTopAppBar] that includes:
 * - A **title**, provided via [screenTitle].
 * - A **navigation icon** (back arrow) that navigates to the previous screen
 *   when clicked, using the current [androidx.navigation.NavController] from [LocalNavController].
 *
 * This component is typically used across multiple screens to maintain a
 * consistent top bar appearance and behavior.
 *
 * Example usage:
 * ```
 * Scaffold(
 *     topBar = { SharedTopBar(screenTitle = "Content Details") }
 * ) { innerPadding ->
 *     // Screen content here
 * }
 * ```
 *
 * @param screenTitle The title text displayed in the center of the top app bar.
 *
 * @see CenterAlignedTopAppBar
 * @see IconButton
 * @see LocalNavController
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTopBar(screenTitle: String) {
    val navController = LocalNavController.current
    CenterAlignedTopAppBar(title = { Text(screenTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}