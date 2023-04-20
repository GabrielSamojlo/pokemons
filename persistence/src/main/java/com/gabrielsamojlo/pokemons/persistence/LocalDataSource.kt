package com.gabrielsamojlo.pokemons.persistence

import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity

interface LocalDataSource {
    suspend fun insertAll(entities: List<PokemonEntity>)
    suspend fun getAll(): List<PokemonEntity>
}
