package com.gabrielsamojlo.pokemons.persistence

import androidx.paging.PagingSource
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonKeyEntity

interface LocalDataSource {
    suspend fun insertAll(entities: List<PokemonEntity>)

    suspend fun insert(entity: PokemonEntity)

    suspend fun getAll(): List<PokemonEntity>
    fun getAllAsPagingSource(): PagingSource<Int, PokemonEntity>

    suspend fun getRemoteKeyByName(name: String): PokemonKeyEntity?

    suspend fun saveRemote(
        shouldWipeData: Boolean,
        lastName: String?,
        offset: Int,
        entites: List<PokemonEntity>
    )
}
