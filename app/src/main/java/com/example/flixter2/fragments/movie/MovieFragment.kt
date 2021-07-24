package com.example.flixter2.fragments.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.flixter2.R
import com.example.flixter2.adapters.MovieAdapter
import com.example.flixter2.databinding.FragmentMovieBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel;

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
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        binding.viewModel = viewModel


        binding.rvMovies.adapter = MovieAdapter()



        return binding.root
    }



}