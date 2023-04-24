package com.gabrielsamojlo.pokemons.domain

import androidx.paging.PagingData
import com.gabrielsamojlo.pokemons.domain.model.pokemon.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.State
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAll(): List<Pokemon>
    fun getById(id: Int?): Flow<State<PokemonDetails>>

    fun getPaginated(): Flow<PagingData<Pokemon>>
}
