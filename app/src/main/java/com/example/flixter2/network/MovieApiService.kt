package com.example.flixter2.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()



interface MovieApiService{
    @GET("now_playing")
    suspend fun getLatestMovies(@Query("api_key") api_key: String,
                                @Query("page") page: Int): LatestMovies


    @GET("{id}/videos")
    suspend fun getYoutubeKey(@Path("id") movieId:
                                  Int, @Query("api_key") api_key: String): YoutubeVideos
}

object MovieApi{
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}