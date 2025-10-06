package com.example.assignment2_multi_screen_app.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class ImageContentViewModel: ViewModel() {
    private val _content = mutableStateListOf<ImageContent>()

    val content: SnapshotStateList<ImageContent> = _content

    fun addContent(name: String, imageURL: String, contentDescription: String) {
        // validate imageURL

//        viewModelScope.launch {
//            if (isImageURL(imageURL)) {
//                _content.add(ImageContent(name, imageURL, contentDescription))
//            }
//        }
        // loosened the validation for image URLs
        if(isValidUrl(imageURL)) {
            _content.add(ImageContent(name,imageURL,contentDescription))
        }
    }

    fun removeContent(name: String) {
        _content.removeIf { it.name  == name }
    }

    // This will have to be a asynchronous function/coroutine
    // because it connects to the internet to validate if it's an image
    private suspend fun isImageURL(imageURL: String): Boolean {

        if(!isValidUrl(imageURL)) {
            return false
        } else {
            // this withContext allows the coroutine to run on a background thread to not overload the UI
            // and it will wait for the result of this code block (true or false in this case)
            return withContext(Dispatchers.IO) {
                try {
                    //creates a URL object
                    val url = URL(imageURL)
                    val connection = url.openConnection() as HttpURLConnection

                    HttpURLConnection.setFollowRedirects(true)
                    connection.instanceFollowRedirects = true

                    connection.requestMethod = "GET"

                    connection.connect()

                    val contentType = connection.contentType
                    val responseCode = connection.responseCode

//                    connection.inputStream.close()

                    println("Response: $responseCode, Content-Type: $contentType, result: ${responseCode in 200..299 && ((contentType != null && contentType.startsWith("image/")) || isProbablyImageURL(imageURL))}")
                    // checks if the connection is accessible, there is a content type and it starts with "image/" as a prefix for image urls
                    val result = responseCode in 200..299 && ((contentType != null && contentType.startsWith("image/")) || isProbablyImageURL(imageURL))
                    connection.disconnect()
                    result
                } catch(e: Exception) {
                    println("ERROR: ${e.message}")
                    false
                }
            }
        }
    }

    private fun isProbablyImageURL(imageURL: String): Boolean {
        return imageURL.lowercase().matches(".*\\.(png|jpg|jpeg|gif|webp)$".toRegex())
    }

    // helper function for checking an imageURL is valid
    private fun isValidUrl(urlString: String): Boolean {
        // checks if the provided string is a valid url format
        return try {
            URL(urlString)
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }
}