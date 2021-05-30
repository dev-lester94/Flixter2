package com.example.flixter2.models

import org.json.JSONArray
import org.json.JSONObject

class Movie(jsonObject: JSONObject) {


    var title: String = jsonObject.getString("title")
        get() = field
    var overview: String = jsonObject.getString("overview")
        get() = field

    var posterPath: String = String.format("https://image.tmdb.org/t/p/w342/%s", jsonObject.getString("poster_path"))
        get() = field

    var backdropPath: String = String.format("https://image.tmdb.org/t/p/w342/%s", jsonObject.getString("backdrop_path"))
        get() = field

    init{
        //title = jsonObject.getString("title")
        //posterPath = jsonObject.getString("poster_path")
        //overview = jsonObject.getString("overview")
    }



    companion object{
        fun fromJsonArray(movieJsonArray: JSONArray): ArrayList<Movie> {
            var movies: ArrayList<Movie> = arrayListOf()
            for (i in 0 until movieJsonArray.length()) {
                movies.add(Movie(movieJsonArray.getJSONObject(i)))
            }

            return movies
        }
    }



}