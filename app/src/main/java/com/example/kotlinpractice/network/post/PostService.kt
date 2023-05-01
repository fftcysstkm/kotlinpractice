package com.example.kotlinpractice.network.post

import com.example.kotlinpractice.domain.Post
import retrofit2.Response
import retrofit2.http.GET

/**
 * 投稿関連のAPIと通信するクラス
 */
interface PostService {


    /**
     * ベースURL
     */
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    /**
     * ユーザー1の投稿一覧を取得
     */
    @GET("users/1/posts")
    suspend fun getPosts(): Response<List<Post>>

}