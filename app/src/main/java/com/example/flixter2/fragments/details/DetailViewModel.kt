package com.example.flixter2.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flixter2.network.*
import com.example.flixter2.utils.Resource

class DetailViewModel(private val movie: Movie, private val repository: MovieApiRepository): ViewModel() {

    private val TAG = "DetailViewModel"

    private var _youtubeKey = repository.getYoutubeKey(movieId = movie.id)
    val youtubeKey: LiveData<out Resource<out YoutubeVideos>>
        get() = _youtubeKey

    private val _seconds = MutableLiveData<Float>()
    val seconds: LiveData<Float>
        get() = _seconds

    private var _playVideo = MutableLiveData<Boolean>()
    val playVideo: LiveData<Boolean>
        get() = _playVideo


    init {
        _seconds.value = 0F
        _playVideo.value = true
        Log.i(TAG, "playVideo: " + playVideo.value.toString())
    }



    fun trackSeconds(currentSecond: Float) {
        _seconds.value = currentSecond
    }

    fun stopVideo() {
        Log.i(TAG, "stopvideo")
        _playVideo.value = false
    }

    fun keepPlaying() {
        Log.i(TAG, "keepplayign")
        _playVideo.value = true
    }


}