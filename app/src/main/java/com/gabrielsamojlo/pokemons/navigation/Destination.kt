package com.gabrielsamojlo.pokemons.navigation

sealed class Destination(val route: String) {
    object PokemonListScreen : Destination("pokemon_list")
    object PokemonDetailsScreen : Destination("pokemon_details") {
        fun withId(id: Int): String {
            return "$route/$id"
        }
    }
}
