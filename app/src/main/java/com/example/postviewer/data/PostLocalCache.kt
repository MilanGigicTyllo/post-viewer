package com.example.postviewer.data

import android.util.Log
import com.example.postviewer.Post

class PostLocalCache {

    private val cache = mutableListOf<Post>()

    fun getById(postId: Int): Post? {
        Log.i("CACHE", "Searching Cache...")
        cache.find { it.id == postId }?.let { Log.i("CACHE", "Cache found for ${it.title}") }
        return cache.find{ post ->
            post.id == postId
        }
    }

    fun insert(post: Post) {
        Log.i("CACHE", "Inserting into Cache")
        cache.add(post)
    }
}