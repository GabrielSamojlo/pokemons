package com.gabrielsamojlo.pokemons.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.gabrielsamojlo.pokemons.MainScreen

@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MAIN_GRAPH_DESTINATION,
        modifier = modifier
    ) {
        navigation(
            route = MAIN_GRAPH_DESTINATION,
            startDestination = Destination.MainScreen.route
        ) {
            composable(Destination.MainScreen.route) {
                MainScreen()
            }
        }
    }
}

private const val MAIN_GRAPH_DESTINATION = "MainGraphDestination"
