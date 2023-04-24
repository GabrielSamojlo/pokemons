package com.gabrielsamojlo.pokemons.feature.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.gabrielsamojlo.pokemons.R
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.domain.model.State
import com.gabrielsamojlo.pokemons.ui.theme.Pink80
import com.gabrielsamojlo.pokemons.ui.theme.Typography

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
        AsyncImage(
            modifier = Modifier
                .size(128.dp)
                .padding(8.dp),
            contentDescription = pokemon.name,
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(pokemon.imageUrl)
                .build(),
        )

        Text(text = pokemon.name.capitalize(Locale.current))
        Text(text = "${pokemon.experience} experience", style = Typography.bodyMedium)

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
fun ErrorView(throwable: Throwable) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_error_outline_24),
            contentDescription = "error icon",
            colorFilter = ColorFilter.tint(Pink80),
            modifier = Modifier.size(192.dp)
        )

        Text(
            text = throwable.message.orEmpty(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(text = "Please try again later.", style = Typography.bodyMedium)
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
