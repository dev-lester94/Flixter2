package com.example.flixter2.network

data class YoutubeVideos (
    val id: Int,
    val results: List<Youtube>
)

data class Youtube (
    val id: String,
    val iso_639_1: String,
    val iso_3166_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
        )
