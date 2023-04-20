package com.gabrielsamojlo.pokemons.domain

import org.koin.dsl.module

val domainModule = module {
    factory<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}
