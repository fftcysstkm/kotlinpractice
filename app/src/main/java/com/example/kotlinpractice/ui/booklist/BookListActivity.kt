package com.example.kotlinpractice.ui.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ActivityBookListBinding
import com.example.kotlinpractice.viewmodels.bookList.BookListViewModel

class BookListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBookListBinding
    private val viewModel: BookListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 一覧データを表示
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        // 削除ボタンで、Firestoreのbookを「利用不可」にする
        val adapter = BookListAdapter { book -> viewModel.updateBookAvailability(book, false) }
        recyclerView.adapter = adapter
        // 区切り線
        val divider = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        recyclerView.addItemDecoration(divider)
        // データ監視
        viewModel.books.observe(this) { books -> adapter.submitList(books) }
        viewModel.fetchBooks()
    }
}