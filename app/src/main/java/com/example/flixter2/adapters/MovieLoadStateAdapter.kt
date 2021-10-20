package com.example.flixter2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flixter2.databinding.ItemMovieLoadStateFooterBinding


class MovieLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemMovieLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )

        return LoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    class LoadStateViewHolder(private val binding: ItemMovieLoadStateFooterBinding, retry: () -> Unit) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textView.isVisible = loadState !is LoadState.Loading
            }
        }

    }

}