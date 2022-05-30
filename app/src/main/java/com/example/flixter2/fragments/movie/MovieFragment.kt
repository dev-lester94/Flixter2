package com.example.flixter2.fragments.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.flixter2.R
import com.example.flixter2.adapters.*
import com.example.flixter2.databinding.FragmentMovieBinding
import com.example.flixter2.di.AppComponent
import com.example.flixter2.di.MyApplication
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import dagger.android.support.AndroidSupportInjection.inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment(R.layout.fragment_movie) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as MyApplication).appComponent.inject(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieViewModel by viewModels {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentMovieBinding = FragmentMovieBinding.bind(view)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val clickListener = BaseViewHolder.ItemSelectedListener {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToDetailFragment(it)
            )
        }

        val adapter = MoviesItemAdapter(clickListener, MovieDiffCallBack())

        binding.apply {
            rvMovies.adapter = adapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter {
                    adapter.retry()
                }
            )

            retryButton.setOnClickListener { adapter.retry() }
        }


        //Set up observers
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(
                viewLifecycleOwner.lifecycle, it
            )
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.apply {
                    progressBar.isVisible = loadStates.refresh is LoadState.Loading
                    statusImage.isVisible = loadStates.refresh is LoadState.Error
                    textView.isVisible = loadStates.refresh is LoadState.Error
                    retryButton.isVisible = loadStates.refresh is LoadState.Error
                }

            }
        }
    }
}