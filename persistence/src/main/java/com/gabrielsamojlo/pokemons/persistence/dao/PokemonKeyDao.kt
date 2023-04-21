package com.gabrielsamojlo.pokemons.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonKeyEntity

@Dao
internal interface PokemonKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(key: PokemonKeyEntity)

    @Query("SELECT * FROM PokemonKeyEntity WHERE name = :name")
    suspend fun getByName(name: String): PokemonKeyEntity?

    @Query("DELETE FROM PokemonKeyEntity")
    suspend fun deleteAll()
}
