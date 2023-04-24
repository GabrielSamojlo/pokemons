package com.gabrielsamojlo.pokemons.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.gabrielsamojlo.pokemons.feature.details.PokemonDetailsScreen
import com.gabrielsamojlo.pokemons.feature.details.PokemonDetailsViewModel
import com.gabrielsamojlo.pokemons.feature.list.PokemonListScreen
import com.gabrielsamojlo.pokemons.feature.list.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
                    error = (state.loadState.refresh as? LoadState.Error)?.error,
                    onPokemonClicked = {
                        navController.navigate(Destination.PokemonDetailsScreen.withId(it.id))
                    },
                )
            }

            composable(
                route = "${Destination.PokemonDetailsScreen.route}/{$POKEMON_ID}",
                arguments = listOf(
                    navArgument(POKEMON_ID) { type = NavType.IntType }
                )
            ) {
                val pokemonId = it.arguments?.getInt(POKEMON_ID)
                val viewModel: PokemonDetailsViewModel = koinViewModel { parametersOf(pokemonId) }
                val state by viewModel.state.collectAsState()

                PokemonDetailsScreen(state)
            }
        }
    }
}

private const val MAIN_GRAPH_DESTINATION = "MainGraphDestination"
private const val POKEMON_ID = "PokemonId"
