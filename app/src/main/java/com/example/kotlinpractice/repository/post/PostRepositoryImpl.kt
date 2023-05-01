package com.example.kotlinpractice.repository.post

import com.example.kotlinpractice.domain.Post
import com.example.kotlinpractice.network.post.PostService
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postService: PostService) : PostRepository {

    /**
     * 一覧を取得する
     */
    override suspend fun getPosts(): Response<List<Post>> {
        return postService.getPosts()
    }

}