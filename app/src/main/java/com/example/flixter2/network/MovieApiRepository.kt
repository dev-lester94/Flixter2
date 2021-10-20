package com.example.flixter2.network

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.flixter2.utils.get


class MovieApiRepository {
    val TAG = "MovieApiRepository"
    fun getLatestMovies() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 100
        ),pagingSourceFactory = {MoviePagingSource()}
    ).liveData

    fun getYoutubeKey(movieId: Int) = get{
        Log.i(TAG, "getyoututbekey")
        MovieApi.retrofitService.getYoutubeKey(movieId = movieId,
            "a07e22bc18f5cb106bfe4cc1f83ad8ed")
    }


}