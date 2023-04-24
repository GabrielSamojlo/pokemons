package com.gabrielsamojlo.pokemons.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatsResponse(
    @SerialName("stat") val stat: StatResponse,
    @SerialName("base_stat") val value: Int = 0
)

