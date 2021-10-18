package com.example.flixter2.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
        private val itemId: Int,
        private val clickListenerId: Int,
        private val binding: ViewDataBinding
        ): RecyclerView.ViewHolder(binding.root) {

    class ItemSelectedListener(val clickListener: (item: Any) -> Unit) {
        fun onClick(item: Any) = clickListener(item)
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