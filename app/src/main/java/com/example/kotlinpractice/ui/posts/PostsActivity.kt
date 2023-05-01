package com.example.kotlinpractice.ui.posts

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpractice.databinding.ActivityPostsBinding
import com.example.kotlinpractice.viewmodels.posts.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 画面遷移してきたら、サーバーから値を取得して、テキスト表示する
 *
 */
@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    // レイアウトバインディング
    private lateinit var binding: ActivityPostsBinding

    // ビューモデル
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // データの変更を監視
        // 投稿一覧データを表示
        val recyclerView = binding.recyclerView
        val adapter = PostsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.posts.observe(this) { posts -> adapter.submitList(posts) }

        // ロード中の場合、プログレス表示
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}