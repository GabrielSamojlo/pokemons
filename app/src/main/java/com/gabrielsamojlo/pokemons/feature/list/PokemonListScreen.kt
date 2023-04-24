package com.gabrielsamojlo.pokemons.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.gabrielsamojlo.pokemons.domain.model.Pokemon
import com.gabrielsamojlo.pokemons.ui.LocalScaffoldState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PokemonListScreen(
    modifier: Modifier,
    isLoading: Boolean,
    error: Throwable?,
    pokemons: LazyPagingItems<Pokemon>,
    onPokemonClicked: (Pokemon) -> Unit
) {
    val scaffoldState = LocalScaffoldState.current

    if (error != null) {
        LaunchedEffect(error.message) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = error.message.orEmpty()
            )
        }
    }

    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(isLoading),
        onRefresh = pokemons::refresh
    ) {
        LazyColumn {
            items(pokemons.itemCount) {
                pokemons[it]?.let { PokemonListItem(pokemon = it, onClicked = onPokemonClicked) }
            }
        }
    }
}

@Composable
private fun PokemonListItem(
    pokemon: Pokemon,
    onClicked: (Pokemon) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClicked.invoke(pokemon) })
    ) {
        Row(
            modifier = Modifier.requiredHeight(96.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(96.dp)
                    .padding(8.dp),
                contentDescription = pokemon.name,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(pokemon.imageUrl)
                    .build(),
            )
            
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = pokemon.name.capitalize(Locale.current)
            )
        }
    }
}
