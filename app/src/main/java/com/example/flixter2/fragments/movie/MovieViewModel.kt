package com.example.flixter2.fragments.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository


class MovieViewModel(private val repository: MovieApiRepository): ViewModel() {

    private val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private val TAG: String = "MovieViewModel"


    private val _movies = repository.getLatestMovies().cachedIn(viewModelScope)

    val movies: LiveData<PagingData<Movie>>
        get() = _movies

    init {
        Log.i(TAG, "Initialize")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}
