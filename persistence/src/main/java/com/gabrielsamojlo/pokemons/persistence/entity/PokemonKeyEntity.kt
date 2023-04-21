package com.gabrielsamojlo.pokemons.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonKeyEntity(
    @PrimaryKey val name: String,
    val offset: Int?
)
