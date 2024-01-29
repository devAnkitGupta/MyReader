package com.example.myreader.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreader.screens.ReaderSplashScreen
import com.example.myreader.screens.home.ReaderHomeScreen
import com.example.myreader.screens.login.ReaderLoginScreen
import com.example.myreader.screens.search.BookSearchViewModel
import com.example.myreader.screens.search.SearchScreen
import com.example.myreader.screens.stats.ReaderStatsScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ReaderScreens.SplashScreen.name,){

        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name){
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name){
            ReaderHomeScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name){
            val viewModel = hiltViewModel<BookSearchViewModel>()
            SearchScreen(navController = navController, viewModel)
        }
    }
}