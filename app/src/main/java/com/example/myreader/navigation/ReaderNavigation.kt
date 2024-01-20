package com.example.myreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreader.screens.ReaderSplashScreen
import com.example.myreader.screens.home.ReaderHomeScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ReaderScreens.SearchScreen.name,){

        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        } 
    }
}