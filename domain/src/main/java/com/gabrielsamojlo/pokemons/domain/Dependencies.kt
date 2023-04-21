package com.gabrielsamojlo.pokemons.domain

import com.gabrielsamojlo.pokemons.persistence.persistenceModule
import com.gabrielsamojlo.pokemons.remote.remoteModule
import org.koin.dsl.module

val domainModule = module {
    includes(remoteModule, persistenceModule)
    factory { RemoteSourceMediator(get(), get()) }
    factory<PokemonRepository> { PokemonRepositoryImpl(get(), get(), get()) }
}
