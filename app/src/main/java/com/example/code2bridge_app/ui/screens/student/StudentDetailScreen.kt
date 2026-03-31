package com.example.code2bridge_app.ui.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.StudentApi
import com.example.code2bridge_app.data.remote.dto.StudentUpdateRequestDto
import com.example.code2bridge_app.viewmodel.StudentViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(studentId: Int, navController: NavController, viewModel: StudentViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var errorMessage : String? by remember { mutableStateOf(null) }
    var successMessage : String? by remember { mutableStateOf(null) }

    val studentApi = RetrofitClient.instance.create(StudentApi::class.java)
    val scope = rememberCoroutineScope()

    val students by viewModel.students.collectAsState()

    // Ingresar datos del estudiante si coincide con el ID, dentro de un LaunchedEffect para no tener bucle infinito
    LaunchedEffect(students) {
        val student = students.find { it.idStudent == studentId }
        if (student != null) {
            name = student.name
            surname = student.surname
            age = student.age.toString()
        }
    }

    if (students.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return // Salimos para no renderizar el resto hasta tener datos
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Estudiante") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        /* Logica para eliminar de forma Soft el registro */
                        scope.launch {
                            try {
                                var response = studentApi.softDeleteStudent(studentId)

                                if (response.isSuccessful) {
                                    viewModel.loadStudents()

                                    navController.popBackStack()
                                    successMessage = "¡Estudiante eliminado con éxito!"

                                    kotlinx.coroutines.delay(1500)
                                    navController.navigate("home") {
                                        popUpTo("studentDetail/$studentId") { inclusive = true }
                                    }
                                } else {
                                    errorMessage = "Error al eliminar estudiante"
                                }
                            } catch (e: Exception) {
                                errorMessage = "Error de conexión: ${e.message}"
                            }
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )
            if (errorMessage != null) {
                Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
            }

            if (successMessage != null) {
                Text(text = successMessage!!, color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    /* Logica de guardar el registro de estudiante */
                    scope.launch {
                        try {
                            var studentDto = StudentUpdateRequestDto(name, surname, age.toInt())

                            var response = studentApi.updateStudent(studentId, studentDto)

                            if (response.isSuccessful && response.body()?.success == true) {
                                viewModel.loadStudents()
                                navController.popBackStack()
                                val studentId = response.body()?.idStudent
                                successMessage = "¡Actualización completada con éxito!"

                                kotlinx.coroutines.delay(3000)
                                navController.navigate("home") {
                                    popUpTo("studentDetail/$studentId") { inclusive = true }
                                }
                            } else {
                                errorMessage = response.body()?.message ?: "Error al actualizar estudiante"
                            }
                        } catch (e: Exception) {
                            errorMessage = "Error de conexión: ${e.message}"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}