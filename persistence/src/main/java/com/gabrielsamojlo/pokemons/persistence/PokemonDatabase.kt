package com.gabrielsamojlo.pokemons.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonDao
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity

@Database(
    version = 1,
    entities = [PokemonEntity::class],
)
internal abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
