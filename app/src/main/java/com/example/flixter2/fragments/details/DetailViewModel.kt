package com.example.flixter2.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.models.Movie
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

class DetailViewModel(movie: Movie): ViewModel() {

    private val VIDEOS_URL =
        "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    val TAG = "DetailViewModel"

    private val _videoId = MutableLiveData<String>()
    val videoId: LiveData<String>
        get() = _videoId

    private val _seconds = MutableLiveData<Float>()
    val seconds: LiveData<Float>
        get() = _seconds




    init {
        Log.i(TAG, movie.title)
        playVideoTrailer(movie)
    }

    override fun onCleared() {

        super.onCleared()
    }

    private fun playVideoTrailer(movie: Movie) {

        val client = AsyncHttpClient()
        client.get(String.format(VIDEOS_URL, movie.movieId),
            object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    Log.d(TAG, "onSuccess")
                    val jsonObject: JSONObject = json!!.jsonObject
                    val results: JSONArray = jsonObject.getJSONArray("results")
                    if (results.length() == 0) {
                        return
                    }

                    _seconds.value = 0F
                    _videoId.value = results.getJSONObject(0).getString("key")

                    //initializeYoutube( videoId, 0F)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Log.d(TAG, "onFailure")
                }
            })
    }

    fun trackSeconds(currentSecond: Float) {
        _seconds.value = currentSecond
    }
}