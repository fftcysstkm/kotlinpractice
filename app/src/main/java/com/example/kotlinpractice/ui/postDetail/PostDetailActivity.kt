package com.example.kotlinpractice.ui.postDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.kotlinpractice.extentions.parcelable
import com.example.kotlinpractice.databinding.ActivityPostDetailBinding
import com.example.kotlinpractice.domain.Post
import com.example.kotlinpractice.viewmodels.postDetail.PostDetailViewModel

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPostDetailBinding
    private val viewModel: PostDetailViewModel by viewModels()

    // intentから値を取り出すキー
    companion object {
        const val EXTRA_POST: String = "KEY_POST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intentから投稿データを取得(拡張関数使用。getParcelableExtra()非推奨のため)
        val postData = intent.parcelable(EXTRA_POST) as? Post

        // 取得した投稿データをViewModelにセット
        postData?.let {
            viewModel.setPostData(it)
        }

        // ViewModelのデータを監視する
        viewModel.post.observe(this) { post ->
            // 取得した投稿データをViewにセット
            binding.detailId.text = post.id.toString()
            binding.detailUserId.text = post.userId.toString()
            binding.detailTitle.text = post.title
            binding.detailBody.text = post.body
        }
    }
}
