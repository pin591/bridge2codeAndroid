package com.example.code2bridge_app.ui.screens.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.code2bridge_app.viewmodel.CourseViewModel
import com.example.code2bridge_app.ui.components.CourseCard
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    mainNavController: NavController?,
    viewModel: CourseViewModel = viewModel()
) {

    val courses by viewModel.courses.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    var showCreateDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCourses()
        viewModel.loadAvailableTitles()   // Cargamos los títulos disponibles
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo Curso")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                error != null -> Text(text = error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
                else -> {
                    LazyColumn(contentPadding = PaddingValues(8.dp)) {
                        items(courses) { course ->
                            CourseCard(
                                course = course,
                                onClick = {
                                    mainNavController?.navigate("courseDetail/${course.idCourse}")
                                },
                                onDelete = {
                                    viewModel.softDeleteCourse(course.idCourse.toLong())
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showCreateDialog) {
        CreateCourseDialog(
            availableTitles = viewModel.availableTitles.collectAsState().value,
            onDismiss = { showCreateDialog = false },
            onCreate = { lookupTitleId, description ->
                viewModel.createCourse(lookupTitleId, description)
                showCreateDialog = false
            }
        )
    }

    successMessage?.let {
        LaunchedEffect(it) {
            kotlinx.coroutines.delay(2000)
            viewModel.clearSuccessMessage()
        }
    }
}