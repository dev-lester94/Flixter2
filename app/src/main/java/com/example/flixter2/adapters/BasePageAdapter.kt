package com.example.flixter2.adapters


import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePageAdapter<T: Any>(
        private val clickListener: BaseViewHolder.ItemSelectedListener,
        comparator: DiffUtil.ItemCallback<T>
): PagingDataAdapter<T, BaseViewHolder<T>>(comparator){

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let{
            holder.bind(it, clickListener)
        }
    }


}

