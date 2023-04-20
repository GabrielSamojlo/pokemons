package com.gabrielsamojlo.pokemons.remote

import com.gabrielsamojlo.pokemons.remote.api.PokemonApi
import com.gabrielsamojlo.pokemons.remote.model.PokemonDetailsResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonResponse

internal class RemoteDataSourceImpl(
    private val pokemonApi: PokemonApi
): RemoteDataSource {

    override suspend fun getPaginated(limit: Int, offset: Int?): List<PokemonResponse> {
        return pokemonApi.getPaginated(limit, offset).results
    }

    override suspend fun getById(id: Int): PokemonDetailsResponse? {
        return pokemonApi.getById(id).body()
    }
}
