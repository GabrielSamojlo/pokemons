package com.gabrielsamojlo.pokemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gabrielsamojlo.pokemons.ui.App
import com.gabrielsamojlo.pokemons.ui.theme.PokemonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonsTheme {
                App(name = getString(R.string.app_name))
            }
        }
    }
}
