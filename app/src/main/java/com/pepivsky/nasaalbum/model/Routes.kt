package com.pepivsky.nasaalbum.model

sealed class Routes(val route: String) {
    object AlbumScreen : Routes("screenAlbum")

    object ImageScreen : Routes("screenImage/{photoURL}") {
        const val KEY_PHOTO_URL = "photoURL"
        fun createRoute(photoURL: String) = "screenImage/$photoURL"
    }

    object Pantalla4 : Routes("pantalla4/{name}") {
        const val KEY_NAME = "name"
        fun createRoute(name: String) = "pantalla4/$name"
    }


}

