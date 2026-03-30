package com.example.code2bridge_app.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.code2bridge_app.ui.screens.tuition.TuitionScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Students : BottomNavItem("students", "Estudiantes", Icons.Default.Person)
    object Courses : BottomNavItem("courses", "Cursos", Icons.Default.Star)
    object Tuitions : BottomNavItem("tuitions", "Matrículas", Icons.Default.CheckCircle)
}

@Composable
fun HomeScreen(navController: NavController? = null) {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Students, BottomNavItem.Courses, BottomNavItem.Tuitions)

    val navBavkStackEntry by navController.currentBackStackEntryAsState()
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
                            navController?.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost (
            navController = navController,
            startDestination = "students",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("students") { StudentScreen() }
            composable("courses") { CourseScreen() }
            composable("tuitions") { TuitionScreen() }
        }
    }
}