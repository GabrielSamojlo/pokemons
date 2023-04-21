package com.gabrielsamojlo.pokemons.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gabrielsamojlo.pokemons.domain.model.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.toDomain
import com.gabrielsamojlo.pokemons.persistence.LocalDataSource
import com.gabrielsamojlo.pokemons.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
internal class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mediator: RemoteSourceMediator
) : PokemonRepository {
    override suspend fun getAll(): List<Pokemon> {
        return remoteDataSource.getPaginated().map { it.toDomain() }
    }

    override suspend fun getById(id: Int): PokemonDetails? {
        return remoteDataSource.getById(id)?.toDomain()
    }

    override fun getPaginated(): Flow<PagingData<Pokemon>> {
        return Pager(
            remoteMediator = mediator,
            config = PagingConfig(pageSize = mediator.pageSize)
        ) {
            localDataSource.getAllAsPagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
