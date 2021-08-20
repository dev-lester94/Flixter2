package com.example.flixter2.adapters

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePageAdapter<T: Any>(
        private val clickListener: BaseViewHolder.ItemSelectedListener?= null,
        comparator: DiffUtil.ItemCallback<T>
): PagingDataAdapter<T, BaseViewHolder<T>>(comparator){

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let{
            holder.bind(it, clickListener)
        }
    }


}

