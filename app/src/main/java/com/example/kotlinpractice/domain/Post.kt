package com.example.kotlinpractice.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 投稿1件を表すクラス
 */
@Parcelize
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
): Parcelable
