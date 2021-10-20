package com.example.flixter2.utils

import android.util.Log
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

private val TAG = "Resources"
data class Resource<T>(val status: STATUS, val data: T? , val message: String?= null){
    enum class STATUS{
        SUCCESS, ERROR, LOADING
    }

    companion object{
        fun<T> success(data: T) = Resource(STATUS.SUCCESS, data)
        fun<T> loading(data: T? = null) = Resource(STATUS.LOADING, data)
        fun error(message: String) = Resource(STATUS.ERROR, null, message = message)
    }
}

fun <T> get(remote: suspend() -> T?) = liveData(Dispatchers.IO){

    //Log.i(TAG, "loading")
    emit(Resource.loading())
    try{
        remote.invoke()?.let {
            //Log.i(TAG, "success")
            emit(Resource.success(it))
        }?: run{
            //Log.i(TAG, "error")
            emit(Resource.error("Network error"))
        }
    }catch(e: Exception){
        emit(Resource.error("Generic error"))
    }
}