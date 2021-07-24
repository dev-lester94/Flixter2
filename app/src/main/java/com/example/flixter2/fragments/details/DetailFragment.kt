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
import androidx.lifecycle.ViewModelProviders
import com.example.flixter2.R
import com.example.flixter2.databinding.FragmentDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    val TAG: String = "DetailFragment"

    private lateinit var tracker: YouTubePlayerTracker

    private lateinit var youTubePlayerView: YouTubePlayerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        var args = DetailFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = DetailViewModelFactory(args.movie)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java)

        binding.movie = args.movie

        Log.i(TAG, args.movie.title)

        (activity as AppCompatActivity).supportActionBar?.title = "Details"

        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        viewModel.videoId.observe(viewLifecycleOwner, Observer { videoId->

                viewModel.seconds.value?.let { seconds->
                    initializeYoutube(videoId, seconds)
                }

        })


        return binding.root
    }


    private fun initializeYoutube(videoId: String,seconds: Float) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                youTubePlayer.loadOrCueVideo(lifecycle, videoId, seconds)
                tracker = YouTubePlayerTracker()
                youTubePlayer.addListener(tracker);

            }
        })
    }

    override fun onDestroy() {
        viewModel.trackSeconds(tracker.currentSecond)
        youTubePlayerView.release()
        super.onDestroy()
    }

}