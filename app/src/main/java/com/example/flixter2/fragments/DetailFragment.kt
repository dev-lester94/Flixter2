package com.example.flixter2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter2.R
import com.example.flixter2.databinding.FragmentDetailBinding
import com.example.flixter2.models.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import kotlin.properties.Delegates


class DetailFragment : Fragment() {

    val TAG: String = "DetailFragment"
    val YOUTUBE_API_KEY: String =  "AIzaSyCzznGRbunINCP989cE7Q599Ds_aCfw5Sk"
    val VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

    lateinit var videoId: String
    var seconds by Delegates.notNull<Float>()

    lateinit var youTubePlayerView: YouTubePlayerView

    //lateinit var youTubePlayerFragment: YouTubePlayerFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        var args = DetailFragmentArgs.fromBundle(requireArguments())

        binding.movie = args.movie

        Log.i(TAG, args.movie.title)

        (activity as AppCompatActivity).supportActionBar?.title = "Details"

        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        val client: AsyncHttpClient = AsyncHttpClient()
        client.get(String.format(VIDEOS_URL, args.movie.movieId), object: JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d(TAG,"onSuccess")
                val jsonObject: JSONObject = json!!.jsonObject
                val results: JSONArray = jsonObject.getJSONArray("results")
                if(results.length() == 0){
                    return
                }

                videoId = results.getJSONObject(0).getString("key")
                initializeYoutube()


            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.d(TAG,"onFailure")
            }
        })




        return binding.root
    }

    private fun initializeYoutube() {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                //youTubePlayerView.videoId
                Log.i(TAG, videoId)

                youTubePlayer.loadVideo(videoId, 0F)

            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("videoId", videoId)

        //youTubePlayerView.getPlayerUiController().getMenu().
        //outState.putFloat("seconds", seconds)
    }


}