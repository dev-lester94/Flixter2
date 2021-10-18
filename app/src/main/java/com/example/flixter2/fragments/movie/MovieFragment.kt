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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.flixter2.R
import com.example.flixter2.adapters.*
import com.example.flixter2.databinding.FragmentMovieBinding
import com.example.flixter2.network.LatestMovies
import com.example.flixter2.network.Movie
import com.example.flixter2.network.MovieApiRepository
import com.example.flixter2.utils.Resource
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

        /*val clickListener = MovieClickListener { movie ->
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie))
        }*/

        val clickListener = BaseViewHolder.ItemSelectedListener{
            if(it is Movie) {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                        it as Movie
                    )
                )
            }
        }

        binding.rvMovies.adapter = MoviesItemAdapter(clickListener,MovieDiffCallBack())

        /* binding.rvMovies.adapter = MovieAdapter(MovieClickListener { movie ->
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie))
        })*/

        //Set up observers
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            (binding.rvMovies.adapter as MoviesItemAdapter<Movie>).submitData(
                    viewLifecycleOwner.lifecycle, it)
        })






        return binding.root
    }



}