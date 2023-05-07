package com.example.kotlinpractice.ui.booklistDataBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ActivityBookListDataBindingBinding
import com.example.kotlinpractice.viewmodels.bookList.BookListViewModel

/**
 * BookListActivityをDataBindingを使ったものに書き換えたもの
 * ViewModelは共通。
 */
class BookListDataBindingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBookListDataBindingBinding
    private val viewModel: BookListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list_data_binding)
        setContentView(binding.root)

        // 一覧データを表示
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        // 削除/復活ボタンで、Firestoreのbookを「利用可かどうか」を反転する
        val adapter = BookListDataBindingAdapter { book -> viewModel.updateBookAvailability(book) }
        recyclerView.adapter = adapter
        // 区切り線
        val divider = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        recyclerView.addItemDecoration(divider)
        // データ監視
        viewModel.books.observe(this) { books -> adapter.submitList(books) }
        viewModel.fetchBooks()
    }
}