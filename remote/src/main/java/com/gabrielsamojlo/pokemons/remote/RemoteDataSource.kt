package com.gabrielsamojlo.pokemons.remote

import com.gabrielsamojlo.pokemons.remote.model.PokemonDetailsResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonResponse

interface RemoteDataSource {
    suspend fun getPaginated(limit: Int = 20, offset: Int? = null): List<PokemonResponse>
    suspend fun getById(id: Int): PokemonDetailsResponse?
}
