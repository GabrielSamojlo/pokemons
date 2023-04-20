package com.gabrielsamojlo.pokemons.remote.model

import com.gabrielsamojlo.pokemons.remote.URL
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val name: String = "",
    val url: String = "",
) {
    val id: Int
        get() = url.replace(URL, "").split("/").last().toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
