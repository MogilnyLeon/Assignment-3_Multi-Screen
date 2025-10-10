package com.example.assignment2_multi_screen_app.data

/**
 * Represents image-related content with metadata.
 *
 * This data class holds basic information about an image, including its
 * display name, source URL, and an accessible description for screen readers
 * or alternative text purposes.
 *
 * @property name The display name or title of the image.
 * @property imageURL The URL pointing to the image resource.
 * @property contentDescription A textual description of the image content,
 * used for accessibility or when the image cannot be displayed.
 */
data class ImageContent (
    val name: String,
    val imageURL: String,
    val contentDescription: String
)