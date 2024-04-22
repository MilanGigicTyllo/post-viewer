package com.example.postviewer.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.Post
import com.example.postviewer.data.PostRepository
import kotlinx.coroutines.launch

class DetailsViewModel() : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post = _post as LiveData<Post>

    fun getPostById(postId: Int) {
        viewModelScope.launch {
            _post.value = PostRepository.getById(postId)
        }
    }
}