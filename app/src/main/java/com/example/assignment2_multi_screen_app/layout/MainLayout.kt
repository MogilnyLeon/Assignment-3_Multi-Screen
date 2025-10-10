package com.example.assignment2_multi_screen_app.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.assignment2_multi_screen_app.LocalNavController

/**
 * A reusable main layout scaffold that provides a consistent app structure.
 *
 * This composable wraps screen content within a [Scaffold] that includes:
 * - A **top app bar** via [SharedTopBar], displaying the provided [screenTitle].
 * - A **bottom navigation bar** via [SharedBottomBar].
 * - A **content area** where the screen’s composable content is displayed.
 *
 * It ensures consistent padding using the [Modifier.padding] provided by the
 * scaffold's inner padding values, maintaining proper spacing between the
 * app bars and the screen content.
 *
 * Example usage:
 * ```
 * MainLayout(screenTitle = "Home") {
 *     Text("Welcome to the home screen!")
 * }
 * ```
 *
 * @param screenTitle The title text displayed in the top app bar.
 * @param content The composable content displayed in the main body of the layout.
 *
 * @see Scaffold
 * @see SharedTopBar
 * @see SharedBottomBar
 */
@Composable
fun MainLayout(
    screenTitle: String,
    content: @Composable () -> Unit
) {
    Scaffold (
        topBar = {SharedTopBar(screenTitle)},
        bottomBar = {SharedBottomBar()}
    ){ 
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}