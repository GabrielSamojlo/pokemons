package com.gabrielsamojlo.pokemons.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielsamojlo.pokemons.domain.PokemonRepository
import com.gabrielsamojlo.pokemons.domain.model.State
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class PokemonDetailsViewModel(
    private val pokemonId: Int?,
    private val repository: PokemonRepository
) : ViewModel() {

    val state = repository.getById(pokemonId)
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading())

}
