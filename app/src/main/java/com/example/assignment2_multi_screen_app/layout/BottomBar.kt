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