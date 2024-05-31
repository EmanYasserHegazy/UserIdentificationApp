package com.user.identificationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.user.identificationapp.ui.screens.SplashScreen
import com.user.identificationapp.ui.screens.UserDetailsScreen
import com.user.identificationapp.ui.screens.UserListScreen
import com.user.identificationapp.ui.screens.UserScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,

    ) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.UserScreen.route) {
            UserScreen(navController)
        }

        composable(NavigationItem.UserList.route) {
            UserListScreen(navController)
        }
        composable(
            route = "${NavigationItem.UserDetails.route}/{userModel}",
            arguments = listOf(navArgument("userModel") { type = NavType.StringType })
        ) {
            UserDetailsScreen(navController)
        }
    }
}