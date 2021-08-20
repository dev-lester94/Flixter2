package com.example.flixter2.adapters

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.flixter2.R
import com.example.flixter2.databinding.ItemMovieBinding
import com.example.flixter2.network.Movie

class MovieViewHolder(private val binding : ItemMovieBinding) : BaseViewHolder<Movie>(binding){

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)

            val binding: ItemMovieBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

            return MovieViewHolder(binding)
        }
    }

    override fun bind(movie: Movie, clickListener: ItemSelectedListener?) {
        binding.movie = movie
        binding.clickListener = clickListener
        lateinit var image: String
        val imgView = binding.ivPoster
        val orientation = imgView.context.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.poster_path)
            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image = String.format("https://image.tmdb.org/t/p/w342/%s", movie.backdrop_path)
            // ...
        }
        Glide.with(imgView.context).load(image).into(imgView)
        //tvTitle.text = movie.title
        binding.executePendingBindings()
    }

}