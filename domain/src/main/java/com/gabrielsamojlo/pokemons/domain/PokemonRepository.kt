package com.gabrielsamojlo.pokemons.domain

import com.gabrielsamojlo.pokemons.domain.model.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.PokemonDetails

interface PokemonRepository {
    suspend fun getAll(): List<Pokemon>
    suspend fun getById(id: Int): PokemonDetails?
}
