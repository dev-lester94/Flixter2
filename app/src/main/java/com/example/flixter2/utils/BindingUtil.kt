package com.example.flixter2.fragments

import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.R
import com.example.flixter2.adapters.MovieAdapter

import com.example.flixter2.network.Movie

/*
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    //recyclerView.adapter = adapter
    //val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

*/

@BindingAdapter("imageUrl")
fun setImage(imgView: ImageView, movie: Movie){
    lateinit var image: String
    val orientation = imgView.context.resources.configuration.orientation
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.poster_path)
        // ...
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.backdrop_path)
        // ...
    }
    Glide.with(imgView.context).load(image).into(imgView)
}

/*
@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView, status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
            Log.i("loading", "breakpoint")
        }
        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
            Log.i("done", "breakpoint")

        }
    }
}
 */