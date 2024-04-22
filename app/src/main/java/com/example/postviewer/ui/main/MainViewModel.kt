package com.example.postviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.Post
import com.example.postviewer.data.PostRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts = _posts as LiveData<List<Post>>

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            _posts.value = PostRepository.getAll()
        }
    }
}