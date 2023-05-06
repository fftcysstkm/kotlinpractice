package com.example.kotlinpractice.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinpractice.databinding.ActivityMainBinding
import com.example.kotlinpractice.ui.booklist.BookListActivity
import com.example.kotlinpractice.ui.posts.PostsActivity

class MainActivity : AppCompatActivity() {

    // バインディング
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // レイアウト設定
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ボタンタップでSecondActivityへ遷移
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PostsActivity::class.java)
            startActivity(intent)
        }
        binding.toBookListButton.setOnClickListener{
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }
}