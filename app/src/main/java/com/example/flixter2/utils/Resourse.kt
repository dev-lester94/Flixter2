package com.example.flixter2.utils

import androidx.lifecycle.LiveData
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

fun <T> get(remote: suspend() -> T?): LiveData<Resource<out T>>{
    return liveData(Dispatchers.IO){
        emit(Resource.loading())
        try {
            remote.invoke()?.let {
                emit(Resource.success<T>(it))
            } ?: run {
                emit(Resource.error("Network error"))
            }
        } catch (e: Exception) {
            emit(Resource.error("Generic error"))
        }
    }
}