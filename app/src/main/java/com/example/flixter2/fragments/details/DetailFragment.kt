package com.example.flixter2.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flixter2.R
import com.example.flixter2.databinding.FragmentDetailBinding
import com.example.flixter2.network.MovieApiRepository
import com.example.flixter2.network.YoutubeVideos
import com.example.flixter2.utils.Resource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    val TAG: String = "DetailFragment"

    private var tracker: YouTubePlayerTracker = YouTubePlayerTracker()

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var listener: YouTubePlayerListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Details"


        var args = DetailFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = DetailViewModelFactory(args.movie, MovieApiRepository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        binding.movie = args.movie

        Log.i(TAG, args.movie.title)

        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        viewModel.youtubeKey.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.STATUS.SUCCESS -> {
                    val key = (it.data as YoutubeVideos).results[0].key
                    //viewModel.keepPlaying()
                    initializeYoutube(key)


                    //val playVideo = viewModel.playVideo.value
                    //Log.i(TAG, "playVideo: " + playVideo.toString())
                    //val seconds = viewModel.seconds.value



                    }



                Resource.STATUS.LOADING -> {
                    //viewModel.stopVideo()

                }
                Resource.STATUS.ERROR -> {
                    //viewModel.stopVideo()
                    Log.i(TAG, "error!!!!!!!")
                }
            }
        })


        return binding.root
    }


    private fun initializeYoutube(youtubeKey: String) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                //tracker = YouTubePlayerTracker()
                val playVideo = viewModel.playVideo.value
                Log.i(TAG, "playVideo: " + playVideo.toString())
                val seconds = viewModel.seconds.value
                if(playVideo == true) {
                    if (seconds != null) {
                        youTubePlayer.loadOrCueVideo(lifecycle, youtubeKey, seconds)
                    }
                    //viewModel.keepPlaying()
                }else{
                    if (seconds != null) {
                        youTubePlayer.cueVideo(youtubeKey,seconds)
                    }
                    //viewModel.stopVideo()
                }

                youTubePlayer.addListener(tracker);

            }
        })
    }

    override fun onDestroy() {
        Log.i(TAG, tracker.state.toString())

        Log.i(TAG, tracker.videoDuration.toString())
        if(tracker.state == PlayerConstants.PlayerState.ENDED ||
            tracker.state == PlayerConstants.PlayerState.VIDEO_CUED){
            viewModel.stopVideo()
        }else if(tracker.state == PlayerConstants.PlayerState.PLAYING){
            viewModel.keepPlaying()
        }
        viewModel.trackSeconds(tracker.currentSecond)
        youTubePlayerView.release()
        super.onDestroy()
    }

}