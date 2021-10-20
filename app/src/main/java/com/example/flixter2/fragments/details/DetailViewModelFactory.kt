package com.example.flixter2.fragments.details

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository

class DetailViewModelFactory(private val movie: Movie, private val repository: MovieApiRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(movie, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}