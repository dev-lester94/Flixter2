package com.example.flixter2.fragments.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flixter2.network.LatestMovies
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MovieApiStatus { LOADING, ERROR, DONE }


class MovieViewModel: ViewModel() {

    private val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private val TAG: String = "MovieViewModel"

    private val _movies = MutableLiveData<ArrayList<Movie>>()

    val movies: LiveData<ArrayList<Movie>>
        get() = _movies

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status



    init {
        Log.i(TAG, "Initialize")
        //_movies.value = arrayListOf()
        //getLatestMovies()
        getLatestMovies()
    }

    private fun getLatestMovies() {

        _status.value = MovieApiStatus.LOADING

        MovieApi.retrofitService.getLatestMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed").enqueue(object:
            Callback<LatestMovies> {
            override fun onResponse(call: Call<LatestMovies>, response: Response<LatestMovies>) {
                _status.value = MovieApiStatus.DONE
                val lastestMovies = response.body()?.results
                _movies.value = (lastestMovies as ArrayList<Movie>?)!!

                //_response.value = "Success: ${lastestMovies} Mars properties retrieved"
            }

            override fun onFailure(call: Call<LatestMovies>, t: Throwable) {
                _status.value = MovieApiStatus.ERROR
                //_response.value = "Failure: " + t.message
                _movies.value = ArrayList()

            }


        })
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}
