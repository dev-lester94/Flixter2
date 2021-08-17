package com.example.flixter2.fragments.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flixter2.R
import com.example.flixter2.adapters.MovieAdapter
import com.example.flixter2.adapters.MovieClickListener
import com.example.flixter2.databinding.FragmentMovieBinding
import com.example.flixter2.network.LatestMovies
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import com.example.flixter2.utils.Resource

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
        (activity as AppCompatActivity).supportActionBar?.title = "Latest Movies"

        val binding: FragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this



        // Giving the binding access to the OverviewViewModel
        val repository = MovieApiRepository()
        viewModel = ViewModelProvider(this, MovieViewModelFactory(repository))
            .get(MovieViewModel::class.java)

        //Set up adapter
        binding.rvMovies.adapter = MovieAdapter(MovieClickListener { movie ->
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie))
        })

        //Set up observers
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.STATUS.SUCCESS -> {
                    Log.i(TAG,"Success: " + it.message)
                    binding.statusImage.visibility = View.GONE
                    val adapter = binding.rvMovies.adapter as MovieAdapter
                    val results = (it.data as LatestMovies).results
                    adapter.submitList(results)
                }
                Resource.STATUS.LOADING -> {
                    Log.i(TAG, "Loading: " + it.message)

                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                Resource.STATUS.ERROR -> {
                    Log.i(TAG,"Error: " + it.message)

                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })






        return binding.root
    }



}