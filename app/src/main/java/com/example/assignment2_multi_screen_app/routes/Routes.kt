package com.example.assignment2_multi_screen_app.routes

/**
 * Defines all navigation routes used within the application.
 *
 * This sealed class represents the different destinations (screens)
 * in the app’s navigation graph. Each route corresponds to a specific
 * screen and may include dynamic path parameters when necessary.
 *
 * The class provides a type-safe way to refer to navigation destinations,
 * reducing errors caused by hardcoded route strings.
 *
 * ### Routes:
 * - [Creation] — Route for the content creation screen.
 * - [DisplayList] — Route for the screen displaying a list of content items.
 * - [DisplayDetails] — Route for the detailed view of a specific content item,
 *   which requires a `name` argument.
 *
 * Example usage:
 * ```
 * navController.navigate(Routes.DisplayDetails.go("MyImage"))
 * ```
 *
 * @property route The navigation route string used by the NavController.
 */
sealed class Routes (val route: String){

    /** Route for the content creation screen. */
    object Creation: Routes("CreationFormRoute")

    /** Route for the screen that displays a list of all content items. */
    object DisplayList: Routes("DisplayListRoute")

    /**
     * Route for displaying details of a specific content item.
     *
     * This route includes a dynamic path parameter (`{name}`) that identifies
     * which content item should be shown.
     *
     * Use the [go] helper function to navigate to this route with a specific name.
     *
     * Example:
     * ```
     * navController.navigate(Routes.DisplayDetails.go("SunsetPhoto"))
     * ```
     */
    object DisplayDetails: Routes("DisplayDetailsRoute/{name}") {
        fun go(name: String) = "DisplayDetailsRoute/$name"
    }
}