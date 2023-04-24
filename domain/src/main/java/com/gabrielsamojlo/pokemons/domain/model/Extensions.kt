package com.gabrielsamojlo.pokemons.domain.model

import com.gabrielsamojlo.pokemons.domain.model.pokemon.Pokemon
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.pokemon.Stat
import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.remote.model.PokemonDetailsResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonResponse

fun PokemonDetailsResponse?.toDomain(): PokemonDetails? {
    this ?: return null
    return PokemonDetails(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        experience = this.experience,
        stats = this.stats.map { Stat(name = it.stat.name, value = it.value) }
    )
}

fun PokemonDetailsResponse.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}

fun PokemonEntity.toDomain(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}

fun PokemonDetails.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}

fun PokemonResponse.toDomain(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}

fun PokemonResponse.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl
    )
}
