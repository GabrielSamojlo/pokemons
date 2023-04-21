package com.gabrielsamojlo.pokemons.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonDao
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonKeyDao
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonKeyEntity

@Database(
    version = 1,
    entities = [PokemonEntity::class, PokemonKeyEntity::class],
)
internal abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonKeyDao(): PokemonKeyDao
}
