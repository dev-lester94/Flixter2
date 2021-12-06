package com.example.flixter2.di

import com.example.flixter2.network.MovieApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory
                .create()).build()

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }
}
