package com.example.flixter2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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

    override fun bind(it: Movie, clickListener: ItemSelectedListener?) {
        binding.movie = it
        binding.clickListener = clickListener
        //tvTitle.text = movie.title
        binding.executePendingBindings()
    }

}