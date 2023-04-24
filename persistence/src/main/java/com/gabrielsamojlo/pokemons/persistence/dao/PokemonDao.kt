package com.gabrielsamojlo.pokemons.persistence.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity

@Dao
internal interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity")
    fun getAllAsPagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM PokemonEntity")
    fun deleteAll()

}
