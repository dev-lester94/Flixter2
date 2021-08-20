package com.example.flixter2.fragments.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flixter2.network.LatestMovies
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApi
import com.example.flixter2.network.MovieApiRepository
import com.example.flixter2.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MovieViewModel(private val repository: MovieApiRepository): ViewModel() {

    private val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private val TAG: String = "MovieViewModel"


    private val _movies = repository.getLatestMovies()

    val movies: LiveData<out Resource<out LatestMovies>>
        get() = _movies




    init {
        Log.i(TAG, "Initialize")
        //_movies.value = arrayListOf()
        //getLatestMovies()
        //getLatestMovies()
    }

   /* private fun getLatestMovies() {

        _movies= repository.getLatestMovies()


    }*/


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}
