package com.gabrielsamojlo.pokemons.navigation

sealed class Destination(val route: String) {
    object MainScreen: Destination("main")
}
