package com.example.flixter2.fragments


import android.content.ContentValues
import android.content.res.Configuration
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
        //image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.backdrop_path)
        //Glide.with(imgView.context).load(image).into(imgView)
        // ...
    }
    //tvTitle.text = movie.title
    //binding.executePendingBindings()
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