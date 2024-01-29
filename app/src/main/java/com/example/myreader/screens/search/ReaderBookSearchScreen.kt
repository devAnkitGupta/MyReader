package com.example.myreader.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myreader.components.InputField
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun SearchScreen(navController: NavController = NavController(LocalContext.current)){
    Scaffold (topBar = {
        ReaderAppBar(title = "Search Books", navController = navController,
            icon = Icons.Default.ArrowBack,
            showProfile = false){
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }
    }) {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            color = Color(0xFFFFFFFF)
        ) {
            Column {
SearchForm(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
){
    Log.d("TAG","SearchScreen: $it")
}
                Spacer(modifier = Modifier.height(13.dp))
                BookList(navController)
            }
        }
    }

}

@Composable
fun BookList(navController: NavController) {
    val listOfBooks = listOf(
        MBook(
            id = "asdf",
            title = "Running",
            authors = "Me & You",
            notes = "hello world",
            photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
        ),
        MBook(
            id = "asdf",
            title = "Running",
            authors = "Me & You",
            notes = "hello world",
            photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
        ),
        MBook(
            id = "asdf",
            title = "Running",
            authors = "Me & You",
            notes = "hello world",
            photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
        ),
    )
   LazyColumn(
       modifier = Modifier.fillMaxSize(),
       contentPadding = PaddingValues(16.dp)
   ){
       items(items = listOfBooks){ book ->
        BookRow(book, navController)
       }

   }
}

@Composable
fun BookRow(book: MBook, navController: NavController) {
  Card (
      modifier = Modifier
          .clickable { }
          .fillMaxWidth()
          .height(100.dp)
          .padding(3.dp),
      shape = RectangleShape,
      elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
  ) {
Row (modifier = Modifier.padding(5.dp),
    verticalAlignment = Alignment.Top
    ) {
   val imageUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
    Image(painter = rememberImagePainter(data = imageUrl), contentDescription = "",
modifier = Modifier.width(80.dp)
    .fillMaxHeight().padding(end = 4.dp)
        )
    Column {
        Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
        Text(text = "Author: ${book.authors}",
overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.labelSmall
            )
    }
}
  }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
){
    val searchQuearyState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQuearyState.value){
        searchQuearyState.value.trim().isNotEmpty()
    }

   InputField(valueState = searchQuearyState, labelId ="Search" , enabled = true,
onAction = KeyboardActions{
    if(!valid) return@KeyboardActions
    onSearch(searchQuearyState.value.trim())
    searchQuearyState.value = ""
    keyboardController?.hide()
}
       )

}