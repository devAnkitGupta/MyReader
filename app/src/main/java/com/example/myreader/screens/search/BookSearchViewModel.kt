package com.example.myreader.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreader.data.DataOrException
import com.example.myreader.data.Resource
import com.example.myreader.model.Item
import com.example.myreader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val repository: BookRepository): ViewModel(){

    var list: List<Item> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)
    init {
        loadBooks()
    }

    private fun loadBooks() {
     searchBooks("Flutter")
    }

     fun searchBooks(query: String) {
         isLoading = true
     viewModelScope.launch {
         if(query.isEmpty()){
             return@launch
         }
         try{
            when(val response = repository.getBooks(query)){
                is Resource.Success -> {
                  list = response.data!!
                  if(list.isNotEmpty()) isLoading = false
                }
                is Resource.Error -> {
                    isLoading = false
                    Log.e("Network", "searchBooks: ${response.message}")
                }
                else ->{ isLoading = false }
            }
         } catch (exceptions: Exception){
             isLoading = false
             Log.d("TAG","searchBooks: ${exceptions.message.toString()}")
         }
     }
    }

}
