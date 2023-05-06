package com.example.kotlinpractice.ui.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.databinding.ItemBookBinding
import com.example.kotlinpractice.domain.Book

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookViewHolder>(BookDiffCallback()) {

    // ViewHolder生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.from(parent)
    }

    // ビューホルダーに値設定
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.titleTextView.text = book.title
            binding.authorTextView.text = book.author
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
