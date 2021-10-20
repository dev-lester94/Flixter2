package com.example.flixter2.fragments


import android.content.ContentValues
import android.content.res.Configuration
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.flixter2.network.Movie

@BindingAdapter("imageUrl")
fun setImage(imgView: ImageView, movie: Movie){
    lateinit var image: String
    val orientation = imgView.context.resources.configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        /*Log.i(ContentValues.TAG, "Portrait!!!! movie.title: ${movie.title}, " +
                "movie.poster_path: ${movie.poster_path}," +
                " movie.backdrop: ${movie.backdrop_path}")*/
        val poster_path = movie.poster_path
        if(poster_path != null) {
            image = String.format("https://image.tmdb.org/t/p/w500/%s", poster_path)
            Glide.with(imgView.context).load(image).into(imgView)
        }else{
            Log.i(ContentValues.TAG, "poster_path is null")
        }
        // ...
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        /*Log.i(ContentValues.TAG, "Landscape!!!! movie.title: ${movie.title}, " +
                "movie.poster_path: ${movie.poster_path}," +
                " movie.backdrop: ${movie.backdrop_path}")*/
        //image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.poster_path)
        val backdrop_path = movie.backdrop_path
        if(backdrop_path != null) {
            image = String.format("https://image.tmdb.org/t/p/w342/%s", backdrop_path)
            Glide.with(imgView.context).load(image).into(imgView)
        }else{
            Log.i(ContentValues.TAG, "poster_path is null")
        }
    }
}
