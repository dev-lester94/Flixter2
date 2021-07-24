package com.example.flixter2.fragments

import android.content.res.Configuration
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.adapters.MovieAdapter
import com.example.flixter2.models.Movie


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = MovieAdapter()
    recyclerView.adapter = adapter
    //val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImage(imgView: ImageView, movie: Movie){
    lateinit var image: String
    val orientation = imgView.context.resources.configuration.orientation
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        image = movie.posterPath
        // ...
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        image = movie.backdropPath
        // ...
    }
    Glide.with(imgView.context).load(image).into(imgView)
}