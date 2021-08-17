package com.example.flixter2.utils

data class Resource<T>(val status: STATUS, val data: T? , val message: String?= null){
    enum class STATUS{
        SUCCESS, ERROR, LOADING
    }

    companion object{
        fun<T> success(data: T) = Resource(STATUS.SUCCESS, data)
        fun<T> loading(data: T? = null) = Resource(STATUS.LOADING, data)
        fun error(message: String) = Resource(STATUS.ERROR, message)
    }
}