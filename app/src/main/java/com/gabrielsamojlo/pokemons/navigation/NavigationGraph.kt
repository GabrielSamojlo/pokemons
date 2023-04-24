package com.gabrielsamojlo.pokemons.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.gabrielsamojlo.pokemons.feature.list.PokemonListScreen
import com.gabrielsamojlo.pokemons.feature.list.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

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
            startDestination = Destination.PokemonListScreen.route
        ) {
            composable(Destination.PokemonListScreen.route) {
                val viewModel: PokemonListViewModel = koinViewModel()
                val state = viewModel.state.collectAsLazyPagingItems()

                PokemonListScreen(
                    modifier = Modifier.fillMaxSize(),
                    isLoading = state.loadState.refresh == LoadState.Loading,
                    pokemons = state,
                    onPokemonClicked = {},
                    error = (state.loadState.refresh as? LoadState.Error)?.error
                )
            }
        }
    }
}

private const val MAIN_GRAPH_DESTINATION = "MainGraphDestination"
