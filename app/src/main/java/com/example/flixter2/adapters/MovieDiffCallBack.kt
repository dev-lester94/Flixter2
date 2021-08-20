package com.example.flixter2.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.flixter2.network.Movie

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    //Two items represent the same movie (id)
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    //Two items that have the same content
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}