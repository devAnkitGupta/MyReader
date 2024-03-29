package com.example.myreader.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myreader.components.FABContent
import com.example.myreader.components.ListCard
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.components.TitleSection
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReaderHomeScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: HomeScreenViewModel
){
   Scaffold (
      topBar = {
  ReaderAppBar(title = "A.Reader", navController = navController)
      },

      floatingActionButton = {
          FABContent{
              navController.navigate(ReaderScreens.SearchScreen.name)
              Log.d("II","NAVIGATE TO SCERCH")
          }
      }
   ) {
     Surface(modifier = Modifier.fillMaxSize()) {
      HomeContent(navController = navController,viewModel)
     }
   }
}

@Composable
fun HomeContent(navController: NavController, viewModel: HomeScreenViewModel){
 var listOfBooks = emptyList<MBook>()
 val currentUser = FirebaseAuth.getInstance().currentUser

 if(!viewModel.data.value.data.isNullOrEmpty()){
     listOfBooks = viewModel.data.value?.data!!.toList()!!.filter {
         mBook ->
         mBook.userId == currentUser?.uid.toString()
     }
     Log.d("Books","HomeContent: ${listOfBooks.toString()}")
 }



//     listOf(
//     MBook(
//         id = "asdf",
//         title = "Running",
//         authors = "Me & You",
//         notes = "hello world",
//         photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
//     ),
//     MBook(
//         id = "asdf",
//         title = "Running",
//         authors = "Me & You",
//         notes = "hello world",
//         photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
//     ),
//     MBook(
//         id = "asdf",
//         title = "Running",
//         authors = "Me & You",
//         notes = "hello world",
//         photoUrl = "https://www.littlethings.info/wp-content/uploads/2014/04/dummy-image-green-e1398449160839.jpg"
//     ),
// )
val currentUserName = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
    FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) else "N/A"
  Column (Modifier.padding(2.dp),
      verticalArrangement = Arrangement.SpaceEvenly
      ) {
      Row (modifier = Modifier.align(alignment = Alignment.Start)) {
          TitleSection(label = "Your reading \n" + "activity now")
          Spacer(modifier = Modifier.fillMaxWidth(0.7f))
          Column {
              Icon(imageVector = Icons.Filled.AccountCircle,
                  contentDescription = "Profile",
modifier = Modifier
    .clickable {
        navController.navigate(ReaderScreens.ReaderStatsScreen.name)
    }
    .size(45.dp),
                  tint =  MaterialTheme.colorScheme.secondary
                  )
              Text(text = currentUserName!!,
modifier = Modifier.padding(2.dp),
                  style = MaterialTheme.typography.labelSmall,
                  color = Color.Red,
                  fontSize = 15.sp,
                  maxLines = 1,
                  overflow = TextOverflow.Clip
                  )
              Divider()
          }
      }
//  ReadingRightNowArea(books = listOf(), navController = navController)
      TitleSection(label = "Reading List")

      BoolListArea(listOfBooks = listOfBooks,navController= navController)
  }
}

@Composable
fun BoolListArea(listOfBooks: List<MBook>, navController: NavController) {

  HorizontalScrollableComponet(listOfBooks){
    navController.navigate(ReaderScreens.UpdateScreen.name + "/$it")
  }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HorizontalScrollableComponet(
    listOfBooks: List<MBook>,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onCardPress: (String) -> Unit) {
  val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(state = scrollState)
    ){

        if(viewModel.data.value.loading == true){
            LinearProgressIndicator()
        } else {
           if(listOfBooks.isNullOrEmpty()){
               Surface {
                   Text(text = "No Books Found. Add a Book",
                       style = TextStyle(
                           color = Color.Red.copy(alpha = 0.4f),
                           fontWeight = FontWeight.Bold,
                           fontSize = 14.sp,
                       )
                       )

               }
           } else {
               for (book in listOfBooks){
                   ListCard(book){
                       onCardPress(it)
                   }
               }
           }
        }


    }
}


//@Composable
//fun ReadingRightNowArea(
//    books: List<MBook>,
//    navController: NavController
//){
//    ListCard()
//
//}
