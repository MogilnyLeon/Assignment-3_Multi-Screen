package com.example.assignment2_multi_screen_app.screens

import android.widget.Toast
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment2_multi_screen_app.data.ImageContentViewModel
import com.example.assignment2_multi_screen_app.layout.MainLayout

/**
 * A screen composable for creating new image content.
 *
 * This composable displays a form where the user can input:
 * - The content's **name**
 * - The **image URL**
 * - A **description** of the content
 *
 * The screen uses [MainLayout] to provide a consistent top bar and bottom bar.
 * The form inputs are displayed in a vertically scrollable [LazyColumn] with spacing.
 * The "Create Content" button is enabled only when all input fields are non-blank.
 *
 * Upon clicking the button, the provided input values are added to the
 * [ImageContentViewModel] via the [onAddContent] helper function, and the input
 * fields are reset to empty strings.
 *
 * State for input fields is persisted across configuration changes using
 * [rememberSaveable].
 *
 * @param contentViewModel The [ImageContentViewModel] responsible for managing
 * the list of image content. Defaults to the current [viewModel].
 *
 * @see MainLayout
 * @see ImageContentViewModel
 */
@Composable
fun ContentCreationScreen(contentViewModel: ImageContentViewModel = viewModel()) {

    var nameValue by rememberSaveable { mutableStateOf("") }
    var URLValue by rememberSaveable { mutableStateOf("") }
    var contentDescValue by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    
    MainLayout("Create") {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth().padding(16.dp)) {

            item {
                Button(
                    onClick = {
                        val success = contentViewModel.addContent(nameValue,URLValue,contentDescValue)
                        if(success){
                            Toast.makeText(context,"Content created successfully!",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,"Failed: Invalid image URL",Toast.LENGTH_SHORT).show()
                        }
                        nameValue = ""
                        URLValue = ""
                        contentDescValue = ""
                    },
                    enabled = nameValue.isNotBlank() && URLValue.isNotBlank() && contentDescValue.isNotBlank()
                ) {
                    Text("Create Content")
                }
                // Name Input field
                TextField(
                    value = nameValue,
                    onValueChange = {nameValue = it},
                    label = { Text("Content Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                // Image URL Input field
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