package com.gabrielsamojlo.pokemons.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gabrielsamojlo.pokemons.domain.model.toEntity
import com.gabrielsamojlo.pokemons.persistence.LocalDataSource
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonKeyEntity
import com.gabrielsamojlo.pokemons.remote.RemoteDataSource

@OptIn(ExperimentalPagingApi::class)
internal class RemoteSourceMediator(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RemoteMediator<Int, PokemonEntity>() {

    val pageSize: Int
        get() = PAGE_SIZE

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val key = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLast(state)
                    if (remoteKey?.offset == null) {
                        MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    remoteKey?.offset
                }
            } ?: 0

            val response = remoteDataSource.getPaginated(limit = PAGE_SIZE, offset = key)

            localDataSource.saveRemote(
                shouldWipeData = loadType == LoadType.REFRESH,
                lastName = response.lastOrNull()?.name,
                offset = key + PAGE_SIZE,
                entites = response.map { it.toEntity() }
            )

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLast(state: PagingState<Int, PokemonEntity>): PokemonKeyEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { pokemon ->
            localDataSource.getRemoteKeyByName(pokemon.name)
        }
    }

    private companion object {
        const val PAGE_SIZE = 15
    }
}
