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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject


class DetailFragment : Fragment() {

    val TAG: String = "DetailFragment"
    val YOUTUBE_API_KEY: String =  "AIzaSyCzznGRbunINCP989cE7Q599Ds_aCfw5Sk"
    val VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"


    lateinit var tracker: YouTubePlayerTracker

    lateinit var youTubePlayerView: YouTubePlayerView

    lateinit var youTubePlayerGlobal: YouTubePlayer

    var orientation = false

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

        var orientation = savedInstanceState?.getBoolean("orientation")
        if(orientation == null || orientation == false){
            val client: AsyncHttpClient = AsyncHttpClient()
            client.get(
                String.format(VIDEOS_URL, args.movie.movieId),
                object : JsonHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                        Log.d(TAG, "onSuccess")
                        val jsonObject: JSONObject = json!!.jsonObject
                        val results: JSONArray = jsonObject.getJSONArray("results")
                        if (results.length() == 0) {
                            return
                        }

                        var videoId = results.getJSONObject(0).getString("key")
                        initializeYoutube( videoId, 0F)


                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.d(TAG, "onFailure")
                    }
                })
        }else{


            var videoId = savedInstanceState?.getString("videoId")
            var seconds = savedInstanceState?.getFloat("seconds")
            if (videoId != null) {
                Log.i(TAG, videoId)
            }
            Log.i(TAG, seconds.toString())
            if (videoId != null) {
                if (seconds != null) {
                    initializeYoutube(videoId, seconds)
                }
            }
        }






        return binding.root
    }

    private fun initializeYoutube(videoId: String,seconds: Float) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                //youTubePlayerView.videoId
                //Log.i(TAG, this@DetailFragment.videoId)

                youTubePlayer.loadVideo(videoId, seconds)
                tracker = YouTubePlayerTracker()
                youTubePlayer.addListener(tracker);
                youTubePlayerGlobal = youTubePlayer


            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("videoId", tracker.videoId)
        outState.putFloat("seconds", tracker.currentSecond)
        //youTubePlayerGlobal.pause()
        outState.putBoolean("orientation",true)

        //youTubePlayerView.getPlayerUiController().getMenu().
        //outState.putFloat("seconds", seconds)
    }




}