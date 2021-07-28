package com.example.flixter2.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.network.Movie
import com.example.flixter2.network.YoutubeApi
import com.example.flixter2.network.YoutubeVideos
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(movie: Movie): ViewModel() {

    private val VIDEOS_URL =
        "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    val TAG = "DetailViewModel"

    private var _youtubeKey = MutableLiveData<String>()
    val youtubeKey: LiveData<String>
        get() = _youtubeKey

    private val _seconds = MutableLiveData<Float>()
    val seconds: LiveData<Float>
        get() = _seconds

    private var _playVideo = MutableLiveData<Boolean>()
    val playVideo: LiveData<Boolean>
        get() = _playVideo


    init {
        Log.i(TAG, movie.title)
        playVideoTrailer(movie)
    }

    override fun onCleared() {

        super.onCleared()
    }

    private fun playVideoTrailer(movie: Movie) {

        YoutubeApi.retrofitService.getYoutubeKey(
            movieId = movie.id,"a07e22bc18f5cb106bfe4cc1f83ad8ed").enqueue(object : Callback<YoutubeVideos>{
            override fun onResponse(call: Call<YoutubeVideos>, response: Response<YoutubeVideos>) {


                _seconds.value = 0F

                val results = response.body()?.results
                _youtubeKey.value = results?.get(0)?.key
                _playVideo.value = true

            }

            override fun onFailure(call: Call<YoutubeVideos>, t: Throwable) {
                Log.d(TAG, "failure")
            }

        })

    }

    fun trackSeconds(currentSecond: Float) {
        _seconds.value = currentSecond
    }

    fun stopVideo() {
        _playVideo.value = false
    }

    fun keepPlaying() {
        _playVideo.value = true
    }
}