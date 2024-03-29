package com.example.flixter2.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.flixter2.network.Movie
import java.lang.IllegalArgumentException

class MoviesItemAdapter<T: Any>(
        clickListener: BaseViewHolder.ItemSelectedListener,
        comparator: DiffUtil.ItemCallback<T>
): BasePageAdapter<T>(clickListener, comparator) {

    companion object{
        const val MOVIE_TYPE = 0
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
            throw IllegalArgumentException("Unknown type")
        }
    }
}