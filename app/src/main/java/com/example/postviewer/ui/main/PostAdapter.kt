package com.example.postviewer.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.Post
import com.example.postviewer.databinding.PostBinding

class PostAdapter(
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: List<Post> = emptyList()
    private var onClickListener: IPostOnClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(list: List<Post>) {
        this.posts = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setOnClickListener(listener: IPostOnClickListener){
        this.onClickListener = listener
    }

    inner class PostViewHolder(private val binding: PostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                tvPostBody.text = post.body
                tvPostTitle.text = post.title
                root.setOnClickListener{
                    onClickListener?.onClick(post)
                }
            }
        }
    }

}

interface IPostOnClickListener{
    public fun onClick(post: Post)
}