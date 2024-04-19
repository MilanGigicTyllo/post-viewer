package com.example.postviewer

data class Post (
    val userId : Int = -1,
    val id: Int = -1,
    val title : String = "Post not found",
    val body : String = "No post data",
)