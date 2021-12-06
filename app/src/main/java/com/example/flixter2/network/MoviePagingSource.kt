package com.example.flixter2.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviePagingSource(private val movieApiService: MovieApiService) : PagingSource<Int, Movie>() {
    private val TAG = "MovingPagingSource"
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        var position = params.key ?: MOVIE_STARTING_PAGE_INDEX
        Log.i(TAG, "position: $position")

        return try{
            val response: LatestMovies = movieApiService.getLatestMovies("a07e22bc18f5cb106bfe4cc1f83ad8ed", position)
            val movies = response.results
            LoadResult.Page(
                    data = movies,
                    prevKey = if(position == MOVIE_STARTING_PAGE_INDEX) null else position -1,
                    nextKey = if(movies.isEmpty()) null else position + 1
            )
        }catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }

    }
}