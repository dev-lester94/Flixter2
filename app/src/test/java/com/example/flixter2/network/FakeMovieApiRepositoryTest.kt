package com.example.flixter2.network

import androidx.lifecycle.map
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.flixter2.adapters.MovieDiffCallBack
import com.example.flixter2.adapters.NoopListCallback
import com.example.flixter2.data.FakeMoveApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FakeMovieApiRepositoryTest{

    private lateinit var fakeMovieApiRepository: FakeMovieApiRepository
    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        fakeMovieApiRepository = FakeMovieApiRepository(FakeMoveApiService())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFakeLatestMovies() = testScope.runTest {
        val latestMovies = fakeMovieApiRepository.getLatestMovies()

        val differ = AsyncPagingDataDiffer(
            diffCallback = MovieDiffCallBack(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )


        val job = launch {
            latestMovies.value?.let {
                differ.submitData(it)
            }
        }
//        if (latestMovies != null) {
//            differ.submitData(latestMovies)
//        }

        advanceUntilIdle()
        assertEquals(1, differ.snapshot().items.size)
        job.cancel()



    }
}