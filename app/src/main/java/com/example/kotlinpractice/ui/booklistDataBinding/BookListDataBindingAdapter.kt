package com.example.kotlinpractice.ui.booklistDataBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ItemBookDataBindingBinding
import com.example.kotlinpractice.domain.Book

/**
 * 本一覧を表示するアダプター(BookListAdapterを、DataBindingを利用したものに書き換えている)
 * @param onComeBackClick 復活ボタンを押したときの処理
 * @param onButtonClick 削除ボタンを押したときの処理
 */
class BookListDataBindingAdapter(private val onButtonClick: (Book) -> Unit) :
    ListAdapter<Book, BookListDataBindingAdapter.BookViewHolder>(BookDiffCallback()) {

    // ViewHolder生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.from(parent)
    }

    // ビューホルダーに値設定
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, onButtonClick)
    }

    class BookViewHolder(private val binding: ItemBookDataBindingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, onButtonClick: (Book) -> Unit) {
            // 本名、著者名、テキストの色など設定不要
            binding.book = book
            // 削除ボタン
            binding.deleteButton.setOnClickListener{
                onButtonClick(book)
            }
            // 復活ボタン
            binding.comeBackButton.setOnClickListener{
                onButtonClick(book)
            }
            // 変数の変化のUIへの反映は、即時ではない時がある
            // RecyclerViewの場合は、強制的に即時反映させる
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): BookViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding:ItemBookDataBindingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_book_data_binding,parent, false)
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
