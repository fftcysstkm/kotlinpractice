package com.example.kotlinpractice.domain

/**
 * 投稿1件を表す
 */
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
