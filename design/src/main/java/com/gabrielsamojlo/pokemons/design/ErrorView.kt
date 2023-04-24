package com.gabrielsamojlo.pokemons.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gabrielsamojlo.pokemons.design.theme.Pink80
import com.gabrielsamojlo.pokemons.design.theme.Typography

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
        Text(text = stringResource(R.string.try_again_later), style = Typography.bodyMedium)
    }
}
