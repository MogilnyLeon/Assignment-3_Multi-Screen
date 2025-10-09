package com.example.assignment2_multi_screen_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignment2_multi_screen_app.LocalNavController
import com.example.assignment2_multi_screen_app.data.ImageContent
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.layout.MainLayout
import com.example.assignment2_multi_screen_app.routes.Routes

@Composable
fun DisplayListScreen(contentViewModel: ImageContentViewModel = viewModel()) {
    val content = contentViewModel.content
    MainLayout("Temp") {
        val navController = LocalNavController.current

        DisplayList(content, contentViewModel::removeContent, navController)
    }
}

@Composable
fun DisplayList(list: List<ImageContent>,
                remove:(String) -> Unit,
                navController: NavController
){
    LazyColumn {
        itemsIndexed(list) {index, item ->
            Card (modifier = Modifier.padding(16.dp)){
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Routes.DisplayDetails.go(item.name))
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("#${index + 1}: ${item.name}", fontSize = 24.sp)
                    IconButton(onClick = {
                        remove(item.name)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Remove Content",
                            Modifier
                                .background(Color.Red, CircleShape)
                                .width(40.dp)
                                .height(40.dp),

                        )
                    }
                }
            }
        }
    }
}