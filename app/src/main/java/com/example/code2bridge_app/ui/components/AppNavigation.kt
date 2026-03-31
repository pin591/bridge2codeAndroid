package com.example.code2bridge_app.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.code2bridge.ui.screens.auth.RegisterScreen
import com.example.code2bridge_app.ui.screens.auth.LoginScreen
import com.example.code2bridge_app.ui.screens.HomeScreen
import com.example.code2bridge_app.ui.screens.student.StudentDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(mainNavController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable(
            route = "studentDetail/{studentId}",
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("studentId") ?: 0
            StudentDetailScreen(studentId = id, navController = navController)
        }
    }
}