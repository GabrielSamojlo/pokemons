package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StatResponse(
    val name: String = ""
)
