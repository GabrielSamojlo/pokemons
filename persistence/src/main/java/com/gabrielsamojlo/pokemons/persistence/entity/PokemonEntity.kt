package com.gabrielsamojlo.pokemons.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val experience: Int? = null,
    // TODO: should add support for stats (as relations)
)
