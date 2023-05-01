package com.example.kotlinpractice.viewmodels.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpractice.domain.Post
import com.example.kotlinpractice.repository.post.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchPosts()
    }

    /**
     * 記事一覧取得
     */
    private fun fetchPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = postRepository.getPosts()
            if (response.isSuccessful) {
                _posts.value = response.body()
            } else {
                // todo エラーハンドリング
            }
            _isLoading.value = false
        }
    }
}