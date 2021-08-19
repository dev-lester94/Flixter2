package com.example.flixter2.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    class ItemSelectedListener(val clickListener: (item: Any) -> Unit) {
        fun onClick(item: Any) = clickListener(item)
    }

    abstract fun bind(it: T, clickListener: ItemSelectedListener?)
}