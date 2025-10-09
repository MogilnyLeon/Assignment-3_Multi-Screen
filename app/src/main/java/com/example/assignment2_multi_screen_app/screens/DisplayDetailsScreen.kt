package com.example.assignment2_multi_screen_app.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.layout.MainLayout

@Composable
fun DisplayDetailsScreen(name: String, contentViewModel: ImageContentViewModel = viewModel()) {
    val item = contentViewModel.readContent(name)
    MainLayout("Temp") {
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text("name: ${item?.name}", modifier = Modifier.padding(16.dp))
            Text("image: ${item?.imageURL}", modifier = Modifier.padding(16.dp))
            Text("content description: ${item?.contentDescription}", modifier = Modifier.padding(16.dp))
        }
    }
}