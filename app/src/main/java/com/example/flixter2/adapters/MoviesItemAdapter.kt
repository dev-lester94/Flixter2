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
        const val IGNORE_TYPE = 1 // IGNORE
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return when(viewType){
            MOVIE_TYPE -> MovieViewHolder.from(parent) as BaseViewHolder<T>
            //IGNORE_TYPE-> IgnoreViewHolder.from(parent) as BaseViewHolder<T>
            else-> throw IllegalArgumentException("Unknown type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item is Movie){
            MOVIE_TYPE
        }else{
            throw IllegalArgumentException("Unknown type")
        }
    }
}