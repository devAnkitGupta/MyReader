package com.example.myreader.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.data.Resource
import com.example.myreader.model.Item
import com.example.myreader.navigation.ReaderScreens
import com.example.myreader.repository.BookRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ProduceStateDoesNotAssignValue")
@Composable
fun BookDetailsScreen(
    navController: NavController,
    bookId: String,
    viewModel: DetailsViewModel = hiltViewModel()
    ){
  Scaffold (
      topBar = {
          ReaderAppBar(title = "Book Details", navController = navController,
              icon = Icons.Default.ArrowBack,
              showProfile = false
              ){
              navController.navigate(ReaderScreens.SearchScreen.name)
          }
      }
  ) {
    Surface (
//        color = Color(0xFFFFFFFF),
        modifier = Modifier.fillMaxSize()
    ) {
     Column (
         modifier = Modifier.padding(top = 12.dp),
         verticalArrangement = Arrangement.Top,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
//         val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()){
//          value = viewModel.getBookInfo(bookId)
//         }.value
//         Spacer(modifier = Modifier.padding(top = 70.dp))
//
//         if(bookInfo.data == null){
//             LinearProgressIndicator()
//         } else {
//             Text(text = "Book id: ${bookInfo.data.volumeInfo!!.title}",)
//         }
     }
    }
  }
}