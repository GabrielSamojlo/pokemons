package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsResponse(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
)
