package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsResponse(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
