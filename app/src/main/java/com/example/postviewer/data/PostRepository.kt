package com.example.postviewer.data

import android.util.Log
import com.example.postviewer.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRepository {

    private const val TAG = "POST_RESPONSE"
    private const val POST_LIST_URL = "https://jsonplaceholder.typicode.com/"

    private val localCache = PostLocalCache()

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
        return localCache.getById(postId) ?: try {
            val response = remoteDataSource.getById(postId)
            if (response.isSuccessful) {
                response.body()?.let { post ->
                    localCache.insert(post)
                    post
                } ?: Post()
            } else {
                Log.e(TAG, response.message())
                Post()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Unknown error")
            Post()
        }
    }


}