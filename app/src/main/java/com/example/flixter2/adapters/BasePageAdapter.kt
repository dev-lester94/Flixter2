package com.example.flixter2.adapters

import android.view.View
import android.view.ViewGroup
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

abstract class BaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view) {
    class ItemSelectedListener(val clickListener: (item: Any) -> Unit) {
        fun onClick(item: Any) = clickListener(item)
    }

    abstract fun bind(it: T, clickListener: BaseViewHolder.ItemSelectedListener?)
}