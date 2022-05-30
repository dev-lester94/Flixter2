package com.example.flixter2.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import com.example.flixter2.network.YoutubeVideos
import com.example.flixter2.utils.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: MovieApiRepository) :
    ViewModel() {

    private val TAG = "DetailViewModel"

    private val movieBacking = MutableLiveData<Movie>()

    private var _youtubeKey: LiveData<out Resource<out YoutubeVideos>> =
        Transformations.switchMap(movieBacking) { movie ->
            repository.getYoutubeKey(movieId = movie.id)
        }

    val youtubeKey: LiveData<out Resource<out YoutubeVideos>>
        get() = _youtubeKey

    private var _seconds: Float


    private var _playVideo: Boolean


    init {
        _seconds = 0F
        _playVideo= true
    }


    fun trackSeconds(currentSecond: Float) {
        _seconds = currentSecond
    }

    fun getSeconds(): Float{
        return _seconds
    }

    fun stopVideo() {
        _playVideo = false
    }

    fun keepPlaying() {
        _playVideo = true
    }

    fun playVideo(): Boolean{
        return _playVideo
    }

    fun setMovie(movie: Movie) {
        movieBacking.value = movie
    }
}