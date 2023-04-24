package com.gabrielsamojlo.pokemons

import com.gabrielsamojlo.pokemons.feature.details.PokemonDetailsViewModel
import com.gabrielsamojlo.pokemons.feature.list.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { params -> PokemonDetailsViewModel(params.get(), get()) }
}
