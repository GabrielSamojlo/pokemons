package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsResponse(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    @SerialName("base_experience") val experience: Int,
    val stats: List<StatsResponse>
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
