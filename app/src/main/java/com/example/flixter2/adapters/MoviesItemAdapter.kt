package com.example.flixter2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flixter2.R
import com.example.flixter2.databinding.ItemMovieBinding
import com.example.flixter2.network.Movie
import java.lang.IllegalArgumentException

class MoviesItemAdapter<T: Any>(
    clickListener: BaseViewHolder.ItemSelectedListener,
    comparator: DiffUtil.ItemCallback<T>
): BasePageAdapter<T>(clickListener, comparator) {

    companion object{
        const val MOVIE_TYPE = 0
        const val TODO_TYPE = 1
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return when(viewType){
            MOVIE_TYPE -> MovieViewHolder.from(parent) as BaseViewHolder<T>
            else-> throw IllegalArgumentException("Unknown type")
        }
    }




    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item is Movie){
            MOVIE_TYPE
        }else{
            TODO_TYPE
        }
    }

}

class MovieViewHolder(private val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)

            val binding: ItemMovieBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);

            return MovieViewHolder(binding)
        }
    }

    fun bind(movie: Movie, clickListener: BaseViewHolder.ItemSelectedListener) {
        //invalidateAll()
        binding.movie =movie
        binding.clickListener = clickListener
        //tvTitle.text = movie.title
        binding.executePendingBindings()

    }

}


class MovieDiffCallBack2 : DiffUtil.ItemCallback<Movie>() {
    //Two items represent the same movie (id)
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    //Two items that have the same content
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}