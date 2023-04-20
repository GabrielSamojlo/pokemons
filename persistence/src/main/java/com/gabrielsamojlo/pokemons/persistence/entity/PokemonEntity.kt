package com.gabrielsamojlo.pokemons.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String ,
    val height: Int,
    val weight: Int,
)
