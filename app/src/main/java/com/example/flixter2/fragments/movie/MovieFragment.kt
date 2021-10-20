package com.example.flixter2.fragments.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.flixter2.R
import com.example.flixter2.adapters.*
import com.example.flixter2.databinding.FragmentMovieBinding
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel;
    val TAG = "MovieFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val repository = MovieApiRepository()
        viewModel = ViewModelProvider(this, MovieViewModelFactory(repository))
            .get(MovieViewModel::class.java)


        val clickListener = BaseViewHolder.ItemSelectedListener{
            if(it is Movie) {
                findNavController().navigate(
                        MovieFragmentDirections.actionMovieFragmentToDetailFragment(it))
            }
        }

        val adapter = MoviesItemAdapter(clickListener,MovieDiffCallBack())

        binding.apply {
            rvMovies.adapter = adapter.withLoadStateFooter(
                    footer = MovieLoadStateAdapter{
                        adapter.retry()
                    }
            )

            retryButton.setOnClickListener { adapter.retry() }
        }


        //Set up observers
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitData(
                    viewLifecycleOwner.lifecycle, it)
        })



        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates->
                binding.apply {
                    progressBar.isVisible = loadStates.refresh is LoadState.Loading
                    statusImage.isVisible = loadStates.refresh is LoadState.Error
                    textView.isVisible = loadStates.refresh is LoadState.Error
                    retryButton.isVisible = loadStates.refresh is LoadState.Error
                }

            }
        }

        return binding.root
    }
}