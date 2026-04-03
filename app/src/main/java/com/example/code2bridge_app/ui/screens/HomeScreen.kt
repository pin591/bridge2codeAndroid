package com.example.code2bridge_app.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.code2bridge_app.ui.screens.student.StudentScreen
import com.example.code2bridge_app.ui.screens.course.CourseScreen
import com.example.code2bridge_app.ui.screens.lookup.LookupScreen
import com.example.code2bridge_app.ui.screens.tuition.TuitionScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Students : BottomNavItem("students", "Estudiantes", Icons.Default.Person)
    object Courses : BottomNavItem("courses", "Cursos", Icons.Default.Star)
    object Tuitions : BottomNavItem("tuitions", "Matrículas", Icons.Default.CheckCircle)
    object Lookups : BottomNavItem("lookups", "Lookups", Icons.Default.Build)
    object LogOut : BottomNavItem("logout", "Logout", Icons.Default.Close)
}

@Composable
fun HomeScreen(mainNavController: NavController? = null) {
    val internalNavController = rememberNavController()
    val items = listOf(BottomNavItem.Students, BottomNavItem.Courses, BottomNavItem.Tuitions, BottomNavItem.Lookups, BottomNavItem.LogOut)

    val navBavkStackEntry by internalNavController.currentBackStackEntryAsState()
    val currentDestination = navBavkStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach {item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            if (item.route == "logout") {
                                if (mainNavController != null) {
                                    mainNavController.navigate("login") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                } else {
                                    internalNavController.navigate("logout")
                                }
                            } else {
                                internalNavController.navigate(item.route) {
                                    popUpTo(internalNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost (
            navController = internalNavController,
            startDestination = "students",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("students") { StudentScreen(
                mainNavController = mainNavController
            ) }
            composable("courses") { CourseScreen(mainNavController = mainNavController) }
            composable("tuitions") { TuitionScreen() }
            composable("lookups") { LookupScreen() }
        }
    }
}