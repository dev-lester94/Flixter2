package com.example.flixter2.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flixter2.network.Movie

class DetailViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}