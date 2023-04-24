package com.gabrielsamojlo.pokemons.navigation

sealed class Destination(val route: String) {
    object PokemonListScreen: Destination("pokemon_list")
}
