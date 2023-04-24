package com.gabrielsamojlo.pokemons.domain

import com.gabrielsamojlo.pokemons.domain.model.State

internal class StateExtractor {

    suspend fun <T> get(dataCall: suspend () -> Result<T?>): State<T> {
        return try {
            val result = dataCall.invoke()
            val data = result.getOrNull()
            val exception = result.exceptionOrNull()

            if (data != null) {
                State.Success(data)
            } else if (exception != null) {
                State.Error(exception)
            } else {
                State.Error(Throwable("Unexpected error occurred."))
            }
        } catch (e: Exception) {
            return State.Error(e)
        }
    }
}
