package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val name: String = "",
    val url: String = "",
)
