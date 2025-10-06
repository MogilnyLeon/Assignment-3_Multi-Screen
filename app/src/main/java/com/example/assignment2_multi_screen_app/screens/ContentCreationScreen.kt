package com.example.assignment2_multi_screen_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.layout.MainLayout

@Composable
fun ContentCreationScreen(contentViewModel: ImageContentViewModel = viewModel()) {
    // this was causing issues: Type 'List<ImageContent>' has no method 'getValue(Nothing?, KProperty0<*>)', so it cannot serve as a delegate
    // val content by contentViewModel.content
    // so I modified the viewmodel to use a snapshot list for the get property.
    val content = contentViewModel.content

    var nameValue by rememberSaveable { mutableStateOf("") }
    var URLValue by rememberSaveable { mutableStateOf("") }
    var contentDescValue by rememberSaveable { mutableStateOf("") }
    MainLayout("Temp") {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // Name Input field
            item {
                Button(
                    onClick = {
                        // need to handle image validation if a URL does not have an image
                        onAddContent(contentViewModel,nameValue,URLValue,contentDescValue)
                        nameValue = ""
                        URLValue = ""
                        contentDescValue = ""
                    },
                    enabled = nameValue.isNotBlank() && URLValue.isNotBlank() && contentDescValue.isNotBlank()
                ) {
                    Text("Create Content")
                }
                TextField(
                    value = nameValue,
                    onValueChange = {nameValue = it},
                    label = { Text("Content Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                // Image URL Input field
                // THERE IS AN ISSUE WITH IMAGE VALIDATION
                TextField(
                    value = URLValue,
                    onValueChange = {URLValue = it},
                    label = { Text("Image URL") },
                    modifier = Modifier.fillMaxWidth()
                )
                // Content Description Input field
                TextField(
                    value = contentDescValue,
                    onValueChange = {contentDescValue = it},
                    label = { Text("Content Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}

fun onAddContent(viewModel: ImageContentViewModel, name: String, imageURL: String,contentDescription: String) {
    viewModel.addContent(name,imageURL,contentDescription)
}