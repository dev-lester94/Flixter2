package com.example.flixter2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.flixter2.R
import com.example.flixter2.databinding.FragmentDetailBinding
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragment


class DetailFragment : Fragment() {

    val TAG: String = "DetailFragment"
    val YOUTUBE_API_KEY: String =  "AIzaSyCzznGRbunINCP989cE7Q599Ds_aCfw5Sk"

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

        /*val youtubePlayerSupportFragment = YoutubeFragment.newInstance("5xVh-7ywKpE")
        fragmentManager?.beginTransaction()
            .reo(R.id.container, youtubePlayerSupportFragment).commit()*/


        val youtubePlayerSupportFragment = YoutubeFragment.newInstance("5xVh-7ywKpE")
        val transaction = requireFragmentManager().beginTransaction()
        transaction.add(R.id.container, youtubePlayerSupportFragment)
        transaction.commit()




        /*val youtubeSupportFragment =
            childFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment?
        youtubeSupportFragment?.initialize(YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    // do any work here to cue video, play video, etc.
                    youTubePlayer.cueVideo("5xVh-7ywKpE")
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                }
            })*/

        /*val onInitializedListener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    youTubePlayer.cueVideo("5xVh-7ywKpE");

                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                }
            }

        val youtubeFragment =
            requireFragmentManager().findFragmentById(R.id.player) as YouTubePlayerFragment?

        if (youtubeFragment != null) {
            youtubeFragment.initialize(YOUTUBE_API_KEY, onInitializedListener)
        }*/


        //val youTubePlayerFragment = fragmentManager!!.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment?
        //youTubePlayerFragment!!.initialize("You_Developer_Api_Key", onInitializedListener)
        //val youTubePlayerFragment: YouTubePlayerFragment? = childFragmentManager!!.findFragmentById(R.id.player) as YouTubePlayerFragment?
        //youTubePlayerFragment!!.initialize(YOUTUBE_API_KEY, onInitializedListener)

        //val currentFragment = NavHostFragment.findNavController(R.id.my).currentDestination?.id

        //binding.player as YouTubePlayerFragment



        //val childFragment = childFragmentManager.findFragmentByTag("fragment_sheet_home") as? YouTubePlayerFragment ?
        //val navHostFragment: Fragment? = fragmentManager?.findFragmentById(R.id.myNavHostFragment)


        //val youTubePlayerFragment = binding.player as YouTubePlayerFragment?
        //youTubePlayerFragment!!.initialize(YOUTUBE_API_KEY, onInitializedListener)

        //val youTubePlayerFragment = YouTubePlayerSupportFragment


        //val movieJSONObject: JSONObject = JSONObject(args.movieString)
        //Log.i(TAG, movieJSONObject.toString())


        Log.i("hello", "hello")

        return binding.root
    }


}