package com.gabrielsamojlo.pokemons.persistence

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonDao
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonKeyDao
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonKeyEntity

internal class LocalDataSourceImpl(
    private val database: PokemonDatabase,
    private val pokemonDao: PokemonDao,
    private val pokemonKeyDao: PokemonKeyDao
): LocalDataSource {

    override suspend fun insert(entity: PokemonEntity) {
        pokemonDao.insert(entity)
    }

    override fun getAllAsPagingSource(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.getAllAsPagingSource()
    }

    override suspend fun getRemoteKeyByName(name: String): PokemonKeyEntity? {
        return pokemonKeyDao.getByName(name)
    }

    override suspend fun saveRemote(
        shouldWipeData: Boolean,
        lastName: String?,
        offset: Int,
        entites: List<PokemonEntity>
    ) {
        database.withTransaction {
            if (shouldWipeData) {
                pokemonDao.deleteAll()
                pokemonKeyDao.deleteAll()
            }

            if (lastName != null) {
                pokemonKeyDao.insert(
                    PokemonKeyEntity(name = lastName, offset = offset)
                )
            }

            pokemonDao.insertAll(entites)
        }
    }
}
