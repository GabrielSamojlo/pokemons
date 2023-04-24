package com.gabrielsamojlo.pokemons.domain

import app.cash.turbine.test
import com.gabrielsamojlo.pokemons.domain.model.State
import com.gabrielsamojlo.pokemons.domain.model.pokemon.PokemonDetails
import com.gabrielsamojlo.pokemons.persistence.LocalDataSource
import com.gabrielsamojlo.pokemons.remote.RemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class PokemonRepositoryImplTest {

    private val remoteSource = mockk<RemoteDataSource>()
    private val localSource = mockk<LocalDataSource>()
    private val mediator = mockk<RemoteSourceMediator>()
    private val extractor = mockk<StateExtractor>()

    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setUp() {
        repository = PokemonRepositoryImpl(
            remoteDataSource = remoteSource,
            localDataSource = localSource,
            mediator = mediator,
            stateExtractor = extractor
        )
    }

    @Test
    fun `when success response is given, loading state should be emitted`() = runTest {
        coEvery { remoteSource.getById(any()) } returns Result.success(mockk())
        coEvery { extractor.get<String>(any()) } returns State.Success("test")

        repository.getById(1).test {
            val loading = awaitItem()
            assert(loading is State.Loading)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when success response is given, after loading state, success should be emitted`() =
        runTest {
            coEvery { extractor.get<PokemonDetails>(any()) } returns State.Success(
                PokemonDetails(
                    id = 0,
                    name = "name",
                    experience = 10,
                    stats = listOf(),
                    imageUrl = ""
                )
            )

            coEvery { localSource.insert(any()) } answers {}

            repository.getById(1).test {
                val loading = awaitItem()
                assert(loading is State.Loading)

                val success = awaitItem()
                assert(success is State.Success)

                awaitComplete()
            }
        }

    @Test
    fun `when error response is given, after loading state, error should be emitted`() = runTest {
        val throwable = Throwable("test")
        coEvery { extractor.get<PokemonDetails>(any()) } returns State.Error(throwable)

        coEvery { localSource.insert(any()) } answers {}

        repository.getById(1).test {
            val loading = awaitItem()
            assert(loading is State.Loading)

            val success = awaitItem()
            assert(success is State.Error)

            awaitComplete()
        }
    }
}
