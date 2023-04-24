package com.gabrielsamojlo.pokemons.domain.model.pokemon

data class PokemonDetails(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val experience: Int,
    val stats: List<Stat>
)
