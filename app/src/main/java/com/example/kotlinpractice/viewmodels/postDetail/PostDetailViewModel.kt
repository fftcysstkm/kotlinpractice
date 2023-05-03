package com.example.kotlinpractice.viewmodels.postDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.domain.Post

class PostDetailViewModel : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> get() = _post

    /**
     * 投稿データをActivityから受け取り、内部にセット
     * @param post 投稿データ
     */
    fun setPostData(post: Post) {
        _post.value = post
    }
}