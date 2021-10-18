package com.example.flixter2.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.flixter2.BR
import com.example.flixter2.R
import com.example.flixter2.databinding.ItemMovieBinding
import com.example.flixter2.network.Movie

class MovieViewHolder(private val binding : ItemMovieBinding) : BaseViewHolder<Movie>(
        BR.movie,
        BR.clickListener,
        binding){

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)

            val binding: ItemMovieBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

            return MovieViewHolder(binding)
        }
    }

}