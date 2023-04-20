@file:OptIn(ExperimentalMaterial3Api::class)

package com.gabrielsamojlo.pokemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.gabrielsamojlo.pokemons.navigation.NavigationGraph
import com.gabrielsamojlo.pokemons.ui.theme.PokemonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = getString(R.string.app_name)) }
                        )
                    }
                ) { paddingValues ->
                    NavigationGraph(Modifier.padding(paddingValues))
                }
            }
        }
    }
}
