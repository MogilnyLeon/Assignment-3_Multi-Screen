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

/**
 * A [ViewModel] responsible for managing a list of [ImageContent] objects.
 *
 * This ViewModel provides functionality for adding, retrieving, and removing
 * image content items. It also includes URL validation logic to ensure that
 * only valid or likely image URLs are stored.
 *
 * The class uses a [SnapshotStateList] to enable reactive UI updates when
 * the content list changes, making it suitable for Jetpack Compose-based UIs.
 */
class ImageContentViewModel: ViewModel() {

    /** Internal mutable list of image content. */
    private val _content = mutableStateListOf<ImageContent>()

    /**
     * Public read-only access to the list of image content.
     *
     * Observing this list allows the UI to automatically update when items
     * are added or removed.
     */
    val content: SnapshotStateList<ImageContent> = _content

    /**
     * Retrieves an [ImageContent] item by its name.
     *
     * @param name The name of the image content to look for.
     * @return The [ImageContent] with the specified name, or `null` if not found.
     */
    fun readContent(name: String): ImageContent? {
        return content.find { item -> item.name == name }
    }

    /**
     * Adds a new [ImageContent] item to the ViewModel's content list if the provided URL is valid.
     *
     * This function performs basic URL validation using [isValidUrl]. If the `imageURL` is valid,
     * a new [ImageContent] object is created with the given [name], [imageURL], and [contentDescription],
     * and added to the internal content list. If the URL is invalid, the content is not added.
     *
     * Unlike the previous implementation, this function does **not** perform asynchronous network
     * validation to check if the URL actually points to an image; it only validates the URL format.
     *
     * @param name The name of the content to add.
     * @param imageURL The URL of the image associated with the content.
     * @param contentDescription A textual description of the content for accessibility or display.
     * @return `true` if the content was successfully added (valid URL), `false` if the URL was invalid
     * and the content was not added.
     *
     * @see ImageContent
     * @see isValidUrl
     */
    fun addContent(name: String, imageURL: String, contentDescription: String): Boolean {
        // validate imageURL

//        viewModelScope.launch {
//            if (isImageURL(imageURL)) {
//                _content.add(ImageContent(name, imageURL, contentDescription))
//            }
//        }
        // loosened the validation for image URLs
        if(isValidUrl(imageURL)) {
            _content.add(ImageContent(name,imageURL,contentDescription))
            return true
        } else
            return false
    }

    /**
     * Removes an [ImageContent] item from the list by its name.
     *
     * @param name The name of the image content to remove.
     */
    fun removeContent(name: String) {
        _content.removeIf { it.name  == name }
    }

    /**
     * Checks if a given URL points to a valid image resource over the network.
     *
     * This function performs a network request and should be called from a coroutine.
     * It uses [Dispatchers.IO] to perform the request off the main thread.
     *
     * @param imageURL The URL to validate.
     * @return `true` if the URL is reachable and points to an image resource,
     * or `false` otherwise.
     */
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

    /**
     * Checks if a URL likely points to an image based on its file extension.
     *
     * @param imageURL The URL to check.
     * @return `true` if the URL ends with a common image file extension, or `false` otherwise.
     */
    private fun isProbablyImageURL(imageURL: String): Boolean {
        return imageURL.lowercase().matches(".*\\.(png|jpg|jpeg|gif|webp)$".toRegex())
    }

    /**
     * Validates that the given string is a properly formatted URL.
     *
     * @param urlString The URL string to validate.
     * @return `true` if the string is a valid URL format, or `false` otherwise.
     */    private fun isValidUrl(urlString: String): Boolean {
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