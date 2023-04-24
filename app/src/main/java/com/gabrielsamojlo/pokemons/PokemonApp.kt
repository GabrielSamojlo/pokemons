package com.gabrielsamojlo.pokemons

import android.app.Application
import com.gabrielsamojlo.pokemons.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApp : Application() {

    private val koinModules = listOf(
        domainModule,
        appModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokemonApp)
            modules(koinModules)
        }
    }
}
