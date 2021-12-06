package com.example.flixter2.network

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.flixter2.BuildConfig
import com.example.flixter2.utils.get
import javax.inject.Inject


class MovieApiRepository @Inject constructor(private val movieApiService: MovieApiService)  {
    private val API_KEY: String = BuildConfig.API_KEY

    val TAG = "MovieApiRepository"
    fun getLatestMovies() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100
        ), pagingSourceFactory = { MoviePagingSource(movieApiService, API_KEY) }
    ).liveData

    fun getYoutubeKey(movieId: Int) = get{
        Log.i(TAG, "getyoututbekey")
        movieApiService.getYoutubeKey(
            movieId = movieId,
            API_KEY
        )
    }


}