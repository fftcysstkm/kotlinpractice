package com.example.kotlinpractice.repository.post

import com.example.kotlinpractice.domain.Post
import retrofit2.Response

/**
 * 投稿のデータソースから投稿を取得する
 */
interface PostRepository {

    /**
     * 投稿一覧を取得する
     */
    suspend fun getPosts():Response<List<Post>>

}