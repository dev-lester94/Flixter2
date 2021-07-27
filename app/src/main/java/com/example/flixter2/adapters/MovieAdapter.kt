package com.example.flixter2.adapters

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.R
import com.example.flixter2.databinding.ItemMovieBinding
import com.example.flixter2.fragments.movie.MovieFragmentDirections
import com.example.flixter2.network.Movie

class MovieAdapter(): ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallBack()) {

    //lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //context = parent.context
        return ViewHolder.from(parent)

        //val binding = ItemMovieBinding.inflate(inflater)
        //return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = getItem(position)
        holder.bind(movie)
    }


   class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                val binding: ItemMovieBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

                return ViewHolder(binding)
            }
        }

        fun bind(movie: Movie) {


            //invalidateAll()
            binding.movie =movie
            //tvTitle.text = movie.title

            binding.executePendingBindings()



            binding.container.setOnClickListener {
                //Toast.makeText(context,"clicked row", Toast.LENGTH_SHORT).show()

                //val movieString: String = movie.toString()
                //Log.i("TAG", movieString)
                it.findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie))
            }



        }

    }
}

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