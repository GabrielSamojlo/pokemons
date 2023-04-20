package com.gabrielsamojlo.pokemons.domain

import com.gabrielsamojlo.pokemons.domain.model.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.toDomain
import com.gabrielsamojlo.pokemons.persistence.LocalDataSource
import com.gabrielsamojlo.pokemons.remote.RemoteDataSource

class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PokemonRepository {
    override suspend fun getAll(): List<Pokemon> {
        return remoteDataSource.getPaginated().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): PokemonDetails? {
        return remoteDataSource.getById(id)?.toDomain()
    }
}
