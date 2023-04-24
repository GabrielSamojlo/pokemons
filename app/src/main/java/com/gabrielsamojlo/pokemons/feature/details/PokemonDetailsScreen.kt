package com.gabrielsamojlo.pokemons.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.gabrielsamojlo.pokemons.R
import com.gabrielsamojlo.pokemons.design.ErrorView
import com.gabrielsamojlo.pokemons.design.PokemonImage
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.State
import com.gabrielsamojlo.pokemons.design.theme.Typography

@Composable
fun PokemonDetailsScreen(
    state: State<PokemonDetails>
) {
    when (state) {
        is State.Error -> ErrorView(state.throwable)
        is State.Loading -> LoadingView()
        is State.Success -> PokemonDetails(state.data)
    }
}

@Composable
private fun PokemonDetails(pokemon: PokemonDetails) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonImage(
            url = pokemon.imageUrl,
            modifier = Modifier
                .size(144.dp)
                .padding(8.dp)
        )

        Text(text = pokemon.name.capitalize(Locale.current))
        Text(
            text = stringResource(
                id = R.string.pokemon_experience,
                pokemon.experience
            ),
            style = Typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(text = "Stats", style = Typography.bodyLarge)

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(pokemon.stats.size) {
                pokemon.stats[it].let {
                    AssistChip(
                        modifier = Modifier.padding(8.dp),
                        onClick = { /* no op */ },
                        label = {
                            Text(text = "${it.name.capitalize(Locale.current)}: ${it.value}")
                        }
                    )
                }
            }
        }
    }
}


@Composable
private fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}
