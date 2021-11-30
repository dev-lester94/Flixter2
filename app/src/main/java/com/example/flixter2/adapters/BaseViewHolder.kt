package com.example.flixter2.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.flixter2.network.Movie

open class BaseViewHolder<T>(
        private val itemId: Int,
        private val clickListenerId: Int,
        private val binding: ViewDataBinding
        ): RecyclerView.ViewHolder(binding.root) {

    class ItemSelectedListener(val clickListener: (item: Movie) -> Unit) {
        fun onClick(item: Movie) = clickListener(item)
    }

    open fun bind(
            item: T,
            clickListener: ItemSelectedListener
    ){
        binding.setVariable(itemId, item)
        binding.setVariable(clickListenerId, clickListener)
        binding.executePendingBindings()
    }
}