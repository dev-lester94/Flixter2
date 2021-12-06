package com.example.flixter2.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService{
    @GET("now_playing")
    suspend fun getLatestMovies(@Query("api_key") api_key: String,
                                @Query("page") page: Int): LatestMovies


    @GET("{id}/videos")
    suspend fun getYoutubeKey(@Path("id") movieId:
                                  Int, @Query("api_key") api_key: String): YoutubeVideos
}
