package com.gabrielsamojlo.pokemons.persistence

import androidx.room.Room
import org.koin.dsl.module

val persistenceModule = module {

    single { Room.databaseBuilder(get(), PokemonDatabase::class.java, "pokemondb").build() }

    single { get<PokemonDatabase>().pokemonDao() }

    factory<LocalDataSource> { LocalDataSourceImpl(get()) }
}
