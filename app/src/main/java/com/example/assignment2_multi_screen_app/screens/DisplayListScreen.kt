package com.example.assignment2_multi_screen_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment2_multi_screen_app.data.ImageContent
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.layout.MainLayout

@Composable
fun DisplayListScreen(contentViewModel: ImageContentViewModel = viewModel()) {
    val content = contentViewModel.content
    MainLayout("Temp") {
        DisplayList(content, contentViewModel::removeContent)
    }
}

@Composable
fun DisplayList(list: List<ImageContent>,
                remove:(String) -> Unit) {
    LazyColumn {
        itemsIndexed(list) {index, item ->
            Row (modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceAround){
                Text("#$index: ${item.name}")
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete, contentDescription = "Remove Content",
                        Modifier.background(Color.Red, CircleShape)
                    )
                }
            }

        }
    }
}