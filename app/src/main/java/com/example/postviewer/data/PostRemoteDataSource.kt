package com.example.postviewer.data

import com.example.postviewer.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostRemoteDataSource {

    @GET("posts")
    suspend fun getAll(): Response<List<Post>>

    @GET("posts/{postId}")
    suspend fun getById(@Path("postId") postId: Int): Response<Post>
}