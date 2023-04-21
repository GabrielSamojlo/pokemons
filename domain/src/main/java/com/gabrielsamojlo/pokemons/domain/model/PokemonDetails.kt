package com.gabrielsamojlo.pokemons.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String
)
