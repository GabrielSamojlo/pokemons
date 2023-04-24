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

    override suspend fun getById(id: Int?): Result<PokemonDetailsResponse?> {
        val result = pokemonApi.getById(id)

        // TODO: This error parsing is very naive - should investigate API documentation for more concrete error handling logic
        return if (result.isSuccessful) {
            Result.success(result.body())
        } else {
            Result.failure(Throwable(result.errorBody()?.string()))
        }
    }
}
