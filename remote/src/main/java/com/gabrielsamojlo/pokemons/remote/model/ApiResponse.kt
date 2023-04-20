package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResponse<T>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T> = emptyList()
)
