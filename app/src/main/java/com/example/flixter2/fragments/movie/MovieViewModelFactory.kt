package com.example.flixter2.fragments.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flixter2.fragments.details.DetailViewModel
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository

class MovieViewModelFactory(private val repository: MovieApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}