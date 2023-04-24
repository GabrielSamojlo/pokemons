package com.gabrielsamojlo.pokemons.domain

import com.gabrielsamojlo.pokemons.domain.model.State
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class StateExtractorTest {

    private lateinit var extractor: StateExtractor

    @Before
    fun setUp() {
        extractor = StateExtractor()
    }

    @Test
    fun `when data is downloaded with success it should be extracted as a State Success`() =
        runTest {
            val dataCall = suspend { Result.success("testData") }
            val state = extractor.get(dataCall)

            Assert.assertEquals("testData", (state as State.Success).data)
        }

    @Test
    fun `when data is downloaded with error it should be propagated to the State Error`() =
        runTest {
            val throwable = Throwable("test")
            val dataCall = suspend { Result.failure<String>(throwable) }
            val state = extractor.get(dataCall)

            Assert.assertEquals(throwable, (state as State.Error).throwable)
        }

    @Test
    fun `when data is downloaded without any data nor exception it should be extracted as State Error`() =
        runTest {
            val dataCall = suspend { Result.success(null) }
            val state = extractor.get(dataCall)

            assert(state is State.Error)
        }

    @Test
    fun `when exception is raised during data download it should be extracted as State Error`() =
        runTest {
            val exception = Exception("test")
            val dataCall: suspend () -> Result<String> = suspend { throw exception }

            val state = extractor.get(dataCall)
            Assert.assertEquals(exception, (state as State.Error).throwable)

        }
}
