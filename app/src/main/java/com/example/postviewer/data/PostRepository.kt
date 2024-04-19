package com.example.postviewer.data

import android.util.Log
import com.example.postviewer.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository {

    private val remoteDataSource: PostRemoteDataSource = Retrofit.Builder()
        .baseUrl(POST_LIST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostRemoteDataSource::class.java)

    suspend fun getAll(): List<Post> {
        return try {
            val response = remoteDataSource.getAll()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                Log.e(TAG, response.message())
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Unknown error")
            emptyList()
        }
    }

    suspend fun getById(postId: Int): Post {
        return try {
            val response = remoteDataSource.getById(postId)
            if (response.isSuccessful) {
                response.body() ?: Post()
            } else {
                Log.e(TAG, response.message())
                Post()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Unknown error")
            Post()
        }
    }

    companion object {
        private const val TAG = "POST_RESPONSE"
        private const val POST_LIST_URL = "https://jsonplaceholder.typicode.com/"
    }

}