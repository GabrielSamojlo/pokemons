package com.gabrielsamojlo.pokemons.domain.model

import com.gabrielsamojlo.pokemons.persistence.entity.PokemonEntity
import com.gabrielsamojlo.pokemons.remote.model.PokemonDetailsResponse
import com.gabrielsamojlo.pokemons.remote.model.PokemonResponse

fun PokemonDetailsResponse.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight
    )
}

fun PokemonDetailsResponse.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight
    )
}

fun PokemonEntity.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight
    )
}

fun PokemonDetails.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight
    )
}

fun PokemonResponse.toDomain(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        url = this.url,
        imageUrl = this.imageUrl
    )
}
