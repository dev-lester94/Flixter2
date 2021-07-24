package com.example.flixter2.fragments.movie

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

class MovieViewModel: ViewModel() {

    private val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private val TAG: String = "MovieViewModel"
    private val _movies = MutableLiveData<ArrayList<Movie>>()

    val movies: LiveData<ArrayList<Movie>>
        get() = _movies


    init {
        Log.i(TAG, "Initialize")
        //_movies.value = arrayListOf()
        getLatestMovies()
    }



    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

    private fun getLatestMovies() {
        val client: AsyncHttpClient = AsyncHttpClient()
        client.get(NOW_PLAY_URL, object: JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d(TAG,"onSuccess")
                val jsonObject: JSONObject = json!!.jsonObject
                val results: JSONArray = jsonObject.getJSONArray("results")
                Log.i(TAG,results.toString())

                _movies.value = Movie.fromJsonArray(results)

                Log.i(TAG, "Movies: "  + (_movies.value?.size ?: 0))


            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.d(TAG,"onFailure")
            }
        })
    }
}