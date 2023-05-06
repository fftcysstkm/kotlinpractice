package com.example.kotlinpractice.domain

data class Book(
    val author: String = "",
    val title: String = "",
    val available: Boolean = false,
    val id: String = ""
)
