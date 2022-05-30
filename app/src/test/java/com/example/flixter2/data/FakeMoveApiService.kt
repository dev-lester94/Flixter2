package com.example.flixter2.data

import com.example.flixter2.network.*

class FakeMoveApiService: MovieApiService {

    override suspend fun getLatestMovies(api_key: String, page: Int): LatestMovies {

        return LatestMovies(
            dates = Date(
                maximum = "2022-06-02",
                minimum = "2022-04-15"
            ),
            page = 1,
            results = listOf(
                Movie(
                    adult = false,
                    backdrop_path = "/gG9fTyDL03fiKnOpf2tr01sncnt.jpg",
                    genre_ids = listOf(
                        28,
                        878,
                        14
                    ),
                    id = 526896,
                    original_language = "en",
                    original_title = "Morbius",
                    overview = "Dangerously ill with" +
                            " a rare blood disorder, and determined " +
                            "to save others suffering his same fate, Dr. Michael Morbius " +
                            "attempts a desperate gamble. What at first appears to be a" +
                            " radical success soon reveals itself to be a remedy potentially " +
                            "worse than the disease.",
                    popularity = 6531.767.toFloat(),
                    poster_path = "/6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg",
                    release_date = "2022-03-30",
                    title = "Morbius",
                    video = false,
                    vote_average = 6.4.toFloat(),
                    vote_count = 1223
                )
            ),
            total_pages = 64,
            total_results = 1261
        )
    }

    override suspend fun getYoutubeKey(movieId: Int, api_key: String): YoutubeVideos {
        TODO("Not yet implemented")
    }
}