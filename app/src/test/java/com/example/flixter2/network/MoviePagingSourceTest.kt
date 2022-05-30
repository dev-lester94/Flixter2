package com.example.flixter2.network

import androidx.paging.PagingSource
import com.example.flixter2.data.FakeMoveApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviePagingSourceTest{
    private lateinit var moviePagingSource: MoviePagingSource
    private val FAKE_API_KEY: String = "FAKE_API_KEY"


    @Before
    fun setUp(){
        moviePagingSource = MoviePagingSource(FakeMoveApiService(), FAKE_API_KEY)
    }

    @Test
    fun testLoad() = runBlocking{
        assertEquals(
            moviePagingSource.load(
                params = PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
            PagingSource.LoadResult.Page(
                listOf(
                        Movie(
                            adult = false,
                            backdrop_path = "/gG9fTyDL03fiKnOpf2tr01sncnt.jpg",
                            genre_ids = listOf(
                                28,
                                878,
                                14
                            ),
                            id = 526896,
                            original_language = "en",
                            original_title = "Morbius",
                            overview = "Dangerously ill with" +
                                    " a rare blood disorder, and determined " +
                                    "to save others suffering his same fate, Dr. Michael Morbius " +
                                    "attempts a desperate gamble. What at first appears to be a" +
                                    " radical success soon reveals itself to be a remedy potentially " +
                                    "worse than the disease.",
                            popularity = 6531.767.toFloat(),
                            poster_path = "/6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg",
                            release_date = "2022-03-30",
                            title = "Morbius",
                            video = false,
                            vote_average = 6.4.toFloat(),
                            vote_count = 1223
                        )

                ),
                prevKey = null,
                nextKey = 2
            )
        )
    }
}