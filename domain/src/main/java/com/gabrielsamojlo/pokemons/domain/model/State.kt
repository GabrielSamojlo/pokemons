package com.gabrielsamojlo.pokemons.domain.model

sealed class State<T> {
    data class Success<T>(val data: T): State<T>()
    data class Error<T>(val throwable: Throwable): State<T>()
    class Loading <T>: State<T>()

    suspend fun onSuccess(block: suspend (data: T) -> Unit) {
        if (this is Success) {
            block.invoke(data)
        }
    }
}
