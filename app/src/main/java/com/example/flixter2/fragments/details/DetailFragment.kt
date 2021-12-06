package com.example.flixter2.fragments.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flixter2.R
import com.example.flixter2.databinding.FragmentDetailBinding
import com.example.flixter2.di.MyApplication
import com.example.flixter2.fragments.movie.MovieViewModel
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
import javax.inject.Inject


class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as MyApplication).appComponent.inject(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DetailViewModel by viewModels {viewModelFactory}

    val TAG: String = "DetailFragment"

    private var tracker: YouTubePlayerTracker = YouTubePlayerTracker()

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var listener: YouTubePlayerListener


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding = FragmentDetailBinding.bind(view)

        var args = DetailFragmentArgs.fromBundle(requireArguments())

        binding.movie = args.movie

        viewModel.setMovie(args.movie)

        youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        viewModel.youtubeKey.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.STATUS.SUCCESS -> {
                    val key = (it.data as YoutubeVideos).results[0].key
                    initializeYoutube(key)
                }

                Resource.STATUS.ERROR -> {
                    youTubePlayerView.release()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun initializeYoutube(youtubeKey: String) {
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val playVideo = viewModel.playVideo.value
                val seconds = viewModel.seconds.value
                if (playVideo == true) {
                    if (seconds != null) {
                        youTubePlayer.loadOrCueVideo(lifecycle, youtubeKey, seconds)
                    }
                } else {
                    if (seconds != null) {
                        youTubePlayer.cueVideo(youtubeKey, seconds)
                    }
                }

                youTubePlayer.addListener(tracker);

            }
        })
    }

    override fun onDestroy() {
        if (tracker.state == PlayerConstants.PlayerState.ENDED ||
            tracker.state == PlayerConstants.PlayerState.VIDEO_CUED
        ) {
            viewModel.stopVideo()
        } else if (tracker.state == PlayerConstants.PlayerState.PLAYING) {
            viewModel.keepPlaying()
        }
        viewModel.trackSeconds(tracker.currentSecond)
        youTubePlayerView.release()
        super.onDestroy()
    }

}