package com.example.myreader.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myreader.components.ReaderLogo

@Composable
fun ReaderLoginScreen(navController: NavController){

 Surface (modifier = Modifier.fillMaxSize()) {
  Column(horizontalAlignment = Alignment.CenterHorizontally,
   verticalArrangement = Arrangement.Top,
   ) {
   ReaderLogo()
  }
 }

}