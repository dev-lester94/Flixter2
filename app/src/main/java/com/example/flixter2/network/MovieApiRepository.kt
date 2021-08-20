package com.example.flixter2.network

import android.util.Log
import androidx.lifecycle.liveData
import com.example.flixter2.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO

class MovieApiRepository {
    val TAG = "MovieApiRepository"
    fun getLatestMovies() =  get{
        Log.i(TAG, "getlatestmovies")
        MovieApi.retrofitService.getLatestMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed")
    }

    fun getYoutubeKey(movieId: Int) = get{
        Log.i(TAG, "getyoututbekey")
        MovieApi.retrofitService.getYoutubeKey(movieId = movieId,
            "a07e22bc18f5cb106bfe4cc1f83ad8ed")
    }

    fun <T> get(remote: suspend() -> T?) = liveData(Dispatchers.IO){
        Log.i(TAG, "loading")
        emit(Resource.loading())
        try{
            remote.invoke()?.let {
                Log.i(TAG, "success")
                emit(Resource.success(it))
            }?: run{
                Log.i(TAG, "error")
                emit(Resource.error("Network error"))
            }
        }catch(e: Exception){
            emit(Resource.error("Generic error"))
        }
    }
}