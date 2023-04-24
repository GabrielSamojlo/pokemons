package com.gabrielsamojlo.pokemons.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.gabrielsamojlo.pokemons.navigation.NavigationGraph

val LocalScaffoldState = compositionLocalOf<ScaffoldState> {
    error("No active scaffold state found!")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(name: String) {
    val scaffoldState = rememberScaffoldState()
    CompositionLocalProvider(
        LocalScaffoldState provides scaffoldState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = name) }
                )
            },
            snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState) }
        ) { paddingValues ->
            NavigationGraph(
                Modifier.padding(paddingValues)
            )
        }
    }
}
