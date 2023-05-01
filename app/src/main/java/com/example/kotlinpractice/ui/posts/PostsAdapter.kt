package com.example.kotlinpractice.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.databinding.ItemPostBinding
import com.example.kotlinpractice.domain.Post

/**
 * 投稿一覧を表示するためのAdapter
 */
class PostsAdapter : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    // ViewHolder生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    // ビューホルダーにデータの値設定
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}
/**
 * ViewHolder
 */
class PostViewHolder(private val binding: ItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * ビューホルダーにデータの値をセットする
     * @param post 投稿１件のデータ
     */
    fun bind(post: Post) {
        binding.titleTextView.text = post.title
        binding.bodyTextView.text = post.body
    }

    companion object {
        fun from(parent:ViewGroup):PostViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
            return PostViewHolder(binding)
        }
    }
}
/**
 * RecyclerViewが再描画するアイテムを検出するために用いられるクラス
 * areItemsTheSameがtrueなら、areContentsTheSameで中身を比較する
 * areContentsTheSameもtrueなら、再描画しない。
 * falseなら、内容が異なると見なされ、その行は再描画される。
 *
 */
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}