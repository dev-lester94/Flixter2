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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.R
import com.example.flixter2.databinding.ItemMovieBinding
import com.example.flixter2.fragments.MovieFragmentDirections
import com.example.flixter2.models.Movie
import org.json.JSONObject

class MovieAdapter(private var movies: List<Movie>, private val context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //context = parent.context
        val inflater = LayoutInflater.from(context)
        //val movieView = inflater.inflate(R.layout.item_movie, parent, false)

        val binding : ItemMovieBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

        return ViewHolder(binding)

        //val binding = ItemMovieBinding.inflate(inflater)
        //return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = movies.get(position)
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(updateMovieList: ArrayList<Movie>) {
        movies = updateMovieList
    }


    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {



        //val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        //val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        //val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(movie: Movie) {


            //invalidateAll()
            binding.movie =movie
            //tvTitle.text = movie.title

            binding.executePendingBindings()

            lateinit var image: String
            val orientation = context.resources.configuration.orientation
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                image = movie.posterPath
                // ...
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                image = movie.backdropPath
                // ...
            }
            Glide.with(context).load(image).into(binding.ivPoster)

            binding.container.setOnClickListener {
                //Toast.makeText(context,"clicked row", Toast.LENGTH_SHORT).show()

                //val movieString: String = movie.toString()
                //Log.i("TAG", movieString)
                it.findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie))
            }



        }

    }


}