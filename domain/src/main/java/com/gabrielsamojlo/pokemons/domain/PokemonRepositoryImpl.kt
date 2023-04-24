package com.gabrielsamojlo.pokemons.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gabrielsamojlo.pokemons.domain.model.pokemon.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.State
import com.gabrielsamojlo.pokemons.domain.model.toDomain
import com.gabrielsamojlo.pokemons.domain.model.toEntity
import com.gabrielsamojlo.pokemons.persistence.LocalDataSource
import com.gabrielsamojlo.pokemons.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
internal class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mediator: RemoteSourceMediator,
    private val stateExtractor: StateExtractor
) : PokemonRepository {

    override fun getById(id: Int?): Flow<State<PokemonDetails>> {
        return flow {
            emit(State.Loading())

            val state = stateExtractor.get { remoteDataSource.getById(id).map { it.toDomain() } }
            state.onSuccess { localDataSource.insert(it.toEntity()) }

            emit(state)
        }
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
