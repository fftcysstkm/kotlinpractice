package com.example.kotlinpractice.viewmodels.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.domain.Book
import com.google.firebase.firestore.FirebaseFirestore

class BookListViewModel :ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private val firestore = FirebaseFirestore.getInstance()

    fun fetchBooks() {
        firestore.collection("books")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.toObjects(Book::class.java)?.let { books ->
                        _books.value = books
                    }
                }
            }
    }
}