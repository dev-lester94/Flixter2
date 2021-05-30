package com.example.flixter2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.R
import com.example.flixter2.adapters.MovieAdapter
import com.example.flixter2.databinding.FragmentMovieBinding
import com.example.flixter2.models.Movie
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    val NOW_PLAY_URL: String = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    val TAG: String = "MovieFragment"

    lateinit var movies: ArrayList<Movie>;
    lateinit var adapter: MovieAdapter;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Latest Movies"

        //Initalize the movies list
        movies = arrayListOf()

        //Lookup for the recyelerview in binding object
        val rvMovies = binding.rvMovies

        //Create the adapter
        adapter = MovieAdapter(movies, activity as AppCompatActivity)
        //SEt the adapter on the recyler view
        rvMovies.adapter = adapter

        //Set a layout Manager on the recyclerview
        rvMovies.layoutManager = LinearLayoutManager(activity as AppCompatActivity)

        val client: AsyncHttpClient = AsyncHttpClient()
        client.get(NOW_PLAY_URL, object: JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d(TAG,"onSuccess")
                val jsonObject: JSONObject = json!!.jsonObject
                val results: JSONArray = jsonObject.getJSONArray("results")
                Log.i(TAG,results.toString())

                movies.addAll(Movie.fromJsonArray(results))
                //adapter.updateMovies(movies)
                //invalidateAll()
                Log.i(TAG, "Movies: "  + movies.size)

                adapter.notifyDataSetChanged()

            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.d(TAG,"onFailure")
            }
        })

        return binding.root
    }

}