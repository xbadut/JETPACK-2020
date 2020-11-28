package com.rizalfahrudin.moviecatalogue.core.data.source.remote

import android.util.Log
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiService
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource private constructor(
    private val apiService: ApiService
){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(
            apiService: ApiService
        ): RemoteDataSource =
            instance
                ?: synchronized(this) {
                    instance
                        ?: RemoteDataSource(apiService)
                }
    }

    suspend fun getMovie(): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovie()
                if (response.movie.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    suspend fun getTv(): Flow<ApiResponse<TvResponse>> {
        return flow {
            try {
                val response = apiService.getTv()
                if (response.tv.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    suspend fun getMovieById(id: Int): Flow<ApiResponse<MovieEntityResponse>> {
        return flow {
            try {
                val response = apiService.getMovieById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    suspend fun getTvById(id: Int): Flow<ApiResponse<TvEntityResponse>> {
        return flow {
            try {
                val response = apiService.getTvById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

}