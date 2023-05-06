package com.example.kotlinpractice.ui.booklist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.databinding.ItemBookBinding
import com.example.kotlinpractice.domain.Book

/**
 * 本一覧を表示するアダプター
 * @param onDeleteClick 削除ボタンを押したときの処理
 */
class BookListAdapter(private val onDeleteClick: (Book) -> Unit) :
    ListAdapter<Book, BookListAdapter.BookViewHolder>(BookDiffCallback()) {

    // ViewHolder生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.from(parent)
    }

    // ビューホルダーに値設定
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, onDeleteClick)
    }

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, onDeleteClick: (Book) -> Unit) {
            // 本名、著者名
            val textColor = if (book.available) {
                Color.BLACK
            } else {
                Color.GRAY
            }
            binding.titleTextView.text = book.title
            binding.titleTextView.setTextColor(textColor)
            binding.authorTextView.text = book.author
            binding.authorTextView.setTextColor(textColor)
            // 削除ボタン
            binding.deleteButton.isEnabled = book.available
            binding.deleteButton.setOnClickListener{
                onDeleteClick(book)
            }

        }

        companion object {
            fun from(parent: ViewGroup): BookViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
                return BookViewHolder(binding)
            }
        }
    }
}

/**
 * RecyclerViewの変更検知用クラス
 */
class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}
