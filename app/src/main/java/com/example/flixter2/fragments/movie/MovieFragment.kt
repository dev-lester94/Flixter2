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

    val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    val TAG: String = "MovieFragment"


    lateinit var adapter: MovieAdapter;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Latest Movies"

        val binding: FragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)

        binding.lifecycleOwner = this

        //Lookup for the recyelerview in binding object
        //val rvMovies = binding.rvMovies

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        binding.viewModel = viewModel
        //adapter = MovieAdapter()
        //binding.rvMovies.adapter = adapter

       ///rvMovies.layoutManager = LinearLayoutManager(activity as AppCompatActivity)

        /*viewModel.movies.observe(viewLifecycleOwner, Observer {
            //Log.i(TAG, it.size.toString())
            adapter.submitList(it)

        })*/

        return binding.root
    }



}