package com.gabrielsamojlo.pokemons.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun PokemonImage(
    url: String,
    modifier: Modifier,
    contentDescription: String? = null
) {
    AsyncImage(
        modifier = modifier,
        contentDescription = contentDescription,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCacheKey(url)
            .crossfade(true)
            .build(),
    )
}
