package com.example.postviewer.ui.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postviewer.Post
import com.example.postviewer.databinding.ActivityMainBinding
import com.example.postviewer.ui.details.DetailsActivity

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.activeNetwork?.let { network: Network ->
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

    } ?: false
}


class MainActivity : AppCompatActivity(), IPostOnClickListener {

    private val viewModel: MainViewModel by viewModels()
    private val adapter = PostAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        adapter.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        viewModel.posts.observe(this) { postList ->
            adapter.setPosts(postList)
        }
    }

    override fun onClick(post: Post) {
        startActivity(Intent(this, DetailsActivity::class.java).also{
            it.putExtra("postId", post.id)
        })
    }

}

