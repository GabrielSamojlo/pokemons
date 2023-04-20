package com.gabrielsamojlo.pokemons.remote.api

import com.gabrielsamojlo.pokemons.remote.model.ApiResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonDetailsResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPaginated(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int? = null,
    ): ApiResponse<PokemonResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getById(
        @Path("id") id: Int
    ): Response<PokemonDetailsResponse>
}
