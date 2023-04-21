package com.gabrielsamojlo.pokemons.persistence

import androidx.room.Room
import com.gabrielsamojlo.pokemons.persistence.dao.PokemonKeyDao
import org.koin.dsl.module

val persistenceModule = module {

    single { Room.databaseBuilder(get(), PokemonDatabase::class.java, "pokemondb").build() }

    single { get<PokemonDatabase>().pokemonDao() }

    single { get<PokemonDatabase>().pokemonKeyDao() }

    factory<LocalDataSource> { LocalDataSourceImpl(get(), get(), get()) }
}
