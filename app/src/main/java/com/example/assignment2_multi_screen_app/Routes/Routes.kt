package com.example.assignment2_multi_screen_app.Routes

sealed class Routes (val route: String){
    object Creation: Routes("CreationFormRoute")
    object DisplayList: Routes("DisplayListRoute")
    object DisplayDetails: Routes("DisplayDetailsRoute/{name}") {
        fun go(name: String) = "DisplayDetailsRoute/$name"
    }
}