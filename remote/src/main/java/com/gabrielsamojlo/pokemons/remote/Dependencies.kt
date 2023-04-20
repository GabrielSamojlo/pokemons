@file:OptIn(ExperimentalSerializationApi::class)

package com.gabrielsamojlo.pokemons.remote

import com.gabrielsamojlo.pokemons.remote.api.PokemonApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

const val URL = "https://pokeapi.co/api/v2/"
private const val CONTENT_TYPE = "application/json"

val remoteModule = module {
    single {
        Json { ignoreUnknownKeys = true }
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }.build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(get<Json>().asConverterFactory(CONTENT_TYPE.toMediaType()))
            .client(get())
            .baseUrl(URL)
            .build()
    }

    single {
        get<Retrofit>().create(PokemonApi::class.java)
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}
