package com.example.postviewer.ui.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.Post
import com.example.postviewer.data.PostRepository
import com.example.postviewer.databinding.ActivityDetailsBinding
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private var viewModel: DetailsViewModel? = null

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = DetailsViewModel(intent.getIntExtra("postId", -1))
    }

    override fun onStart() {
        super.onStart()
        viewModel?.post?.observe(this){ post ->
            setupPost(post)
        }
    }

    private fun setupPost(post: Post){
        with(binding){
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
            tvUserId.text = post.userId.toString()
        }
    }
}

