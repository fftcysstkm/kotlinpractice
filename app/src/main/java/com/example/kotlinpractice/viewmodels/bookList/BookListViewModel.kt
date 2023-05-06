package com.example.kotlinpractice.viewmodels.bookList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.domain.Book
import com.google.firebase.firestore.FirebaseFirestore

class BookListViewModel : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private val firestore = FirebaseFirestore.getInstance()

    /**
     * 本を削除する（availableをfalseにする）
     * 更新成功したら本を取得し直す。
     */
    fun updateBookAvailability(book: Book, available: Boolean) {
        firestore.collection("books")
            .document(book.id)
            .update("available", available)
            .addOnSuccessListener {
                fetchBooks()
            }
            .addOnFailureListener { e -> Log.w("BookListViewModel", "Error updating document", e) }
    }

    /**
     * 本一覧を取得
     */
    fun fetchBooks() {
        firestore.collection("books")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val books = task.result?.documents?.mapNotNull { document ->
                        document.toObject(Book::class.java)?.copy(id = document.id) ?: Book()
                    }
                    _books.value = books ?: emptyList()
                }
            }
    }
}