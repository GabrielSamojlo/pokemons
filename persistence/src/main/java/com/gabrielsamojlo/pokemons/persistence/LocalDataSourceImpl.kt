package com.gabrielsamojlo.pokemons.persistence

import com.gabrielsamojlo.pokemons.persistence.dao.PokemonDao
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity

internal class LocalDataSourceImpl(
    private val pokemonDao: PokemonDao
): LocalDataSource {

    override suspend fun insertAll(entities: List<PokemonEntity>) {
        pokemonDao.insertAll(entities)
    }

    override suspend fun getAll(): List<PokemonEntity> {
        return pokemonDao.getAll()
    }
}
