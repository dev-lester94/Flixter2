package com.example.flixter2.fragments.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import javax.inject.Inject


class MovieViewModel @Inject constructor(private val repository: MovieApiRepository): ViewModel() {
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
