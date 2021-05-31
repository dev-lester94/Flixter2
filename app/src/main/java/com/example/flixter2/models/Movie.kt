package com.example.flixter2.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


import kotlinx.android.parcel.RawValue
import kotlinx.serialization.Serializable
import org.json.JSONArray
import org.json.JSONObject
import org.parceler.Parcel



@Parcelize
class Movie(private val tempTitle: String, private val tempOverview: String, private val tempPosterPath: String,
            private val tempBackDrop: String, private val tempRating: Double): Parcelable{


    var title: String = tempTitle
        get() = field
    var overview: String = tempOverview
        get() = field

    var posterPath: String = tempPosterPath
        get() = String.format("https://image.tmdb.org/t/p/w342/%s", field)

    var backdropPath: String = tempBackDrop
        get() = String.format("https://image.tmdb.org/t/p/w342/%s", field)

    var rating: Float = tempRating.toFloat()
        get() = field



    companion object{
        fun fromJsonArray(movieJsonArray: JSONArray): ArrayList<Movie> {
            var movies: ArrayList<Movie> = arrayListOf()
            for (i in 0 until movieJsonArray.length()) {
                var title: String = movieJsonArray.getJSONObject(i).getString("title")
                var overview: String = movieJsonArray.getJSONObject(i).getString("overview")
                var posterPath: String = movieJsonArray.getJSONObject(i).getString("poster_path")
                var backdropPath: String = movieJsonArray.getJSONObject(i).getString("backdrop_path")

                var rating: Double = movieJsonArray.getJSONObject(i).getDouble("vote_average")
                movies.add(Movie(title, overview, posterPath, backdropPath, rating))
            }

            return movies
        }
    }


}