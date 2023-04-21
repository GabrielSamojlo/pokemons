package com.gabrielsamojlo.pokemons.domain

import androidx.paging.PagingData
import com.gabrielsamojlo.pokemons.domain.model.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAll(): List<Pokemon>
    suspend fun getById(id: Int): PokemonDetails?

    fun getPaginated(): Flow<PagingData<Pokemon>>
}
