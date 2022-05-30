package com.example.flixter2.fragments.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.*
import com.example.flixter2.adapters.BaseViewHolder
import com.example.flixter2.adapters.MovieDiffCallBack
import com.example.flixter2.adapters.MoviesItemAdapter
import com.example.flixter2.adapters.NoopListCallback
import com.example.flixter2.data.FakeMoveApiService
import com.example.flixter2.network.FakeMovieApiRepository
import com.example.flixter2.network.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest{

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var fakeMovieApiRepository: FakeMovieApiRepository
    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        fakeMovieApiRepository = FakeMovieApiRepository(FakeMoveApiService())
        movieViewModel = MovieViewModel(
            repository = fakeMovieApiRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFakeLatestMovies() = testScope.runTest{
        val latestMovies = movieViewModel.movies
//       assertNotNull(latestMovies)

//        val clickListener = BaseViewHolder.ItemSelectedListener {}
//        val adapter = MoviesItemAdapter(clickListener, MovieDiffCallBack())
        //val movies = fakeMovieApiRepository.getLatestMovies()
        val differ = AsyncPagingDataDiffer(
            diffCallback = MovieDiffCallBack(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )

        val job = launch {
            latestMovies.observeForever{
                runBlocking {
                    differ.submitData(it)
                }
            }
        }

        //advanceUntilIdle()
        assertEquals(1, differ.snapshot().items.size)
        job.cancel()



//        val differ = AsyncPagingDataDiffer(
//            diffCallback = MovieDiffCallBack(),
//            updateCallback = NoopListCallback(),
//            workerDispatcher = Dispatchers.Main
//        )
//
//
//        latestMovies.value?.let {
//            differ.submitData(it)
//        }
//        advanceUntilIdle()
//        assertEquals(1, differ.snapshot())
        //assertEquals("/gG9fTyDL03fiKnOpf2tr01sncnt.jpg", adapter.snapshot().items[0].backdrop_path)


    }
}