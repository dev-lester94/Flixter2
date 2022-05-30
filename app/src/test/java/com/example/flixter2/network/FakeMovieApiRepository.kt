package com.example.flixter2.network

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.flixter2.BuildConfig
import com.example.flixter2.data.FakeMoveApiService

class FakeMovieApiRepository(private val fakeMovieApiService: FakeMoveApiService) : MovieApiRepository(fakeMovieApiService) {
    private val FAKE_API_KEY: String = "FAKE_API_KEY"

    override fun getLatestMovies() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100
        ), pagingSourceFactory = { MoviePagingSource(fakeMovieApiService, FAKE_API_KEY) }
    ).liveData
}