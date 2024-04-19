package com.example.postviewer.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.Post
import com.example.postviewer.data.PostRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val postId: Int) : ViewModel() {

    private val postRepository = PostRepository()

    private val _post = MutableLiveData<Post>()
    val post = _post as LiveData<Post>

    init {
        getPostById(postId)
    }

    private fun getPostById(postId: Int) {
        viewModelScope.launch {
            _post.value = postRepository.getById(postId)
        }
    }
}