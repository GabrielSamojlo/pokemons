package com.gabrielsamojlo.pokemons.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gabrielsamojlo.pokemons.domain.PokemonRepository

class PokemonListViewModel(repository: PokemonRepository): ViewModel() {

    val state = repository.getPaginated().cachedIn(viewModelScope)
}
