package com.example.myreader.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myreader.components.FABContent
import com.example.myreader.components.ListCard
import com.example.myreader.components.ReaderAppBar
import com.example.myreader.components.TitleSection
import com.example.myreader.model.MBook
import com.example.myreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ReaderHomeScreen(navController: NavController = NavController(LocalContext.current)){
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
      HomeContent(navController = navController)
     }
   }
}

@Composable
fun HomeContent(navController: NavController){
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
  ReadingRightNowArea(books = listOf(), navController = navController)
      TitleSection(label = "Reading List")

      BoolListArea(listOfBooks = listOfBooks,navController= navController)
  }
}

@Composable
fun BoolListArea(listOfBooks: List<MBook>, navController: NavController) {

  HorizontalScrollableComponet(listOfBooks){

  }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HorizontalScrollableComponet(
    listOfBooks: List<MBook>,
    onCardPress: (String) -> Unit) {
  val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(state = scrollState)
    ){
       for (book in listOfBooks){
           ListCard(book){
            onCardPress(it)
           }
       }
    }
}


@Composable
fun ReadingRightNowArea(
    books: List<MBook>,
    navController: NavController
){
    ListCard()

}
