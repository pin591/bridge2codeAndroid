package com.example.code2bridge_app.ui.screens.course

import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
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
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.viewmodel.CourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    courseId: Int,
    navController: NavController,
    viewModel: CourseViewModel = viewModel()
) {
    // 1. Estados locales
    var description by remember { mutableStateOf("") }
    var selectedTitle by remember { mutableStateOf<LookupLine?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    // 2. Estados del ViewModel
    val courses by viewModel.courses.collectAsState()
    val availableTitles by viewModel.availableTitles.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // 3. LOG DE CONTROL (Para que veas en el Logcat si llega el ID)
    LaunchedEffect(courseId) {
        println("DEBUG: Entrando a CourseDetail con ID: $courseId")
    }

    // 4. Sincronizar datos: Cuando la lista de cursos o títulos cambie
    LaunchedEffect(courses, availableTitles) {
        if (courses.isNotEmpty()) {
            val foundCourse = courses.find { it.idCourse == courseId }
            if (foundCourse != null) {
                description = foundCourse.description
                // Buscamos la coincidencia exacta en la lista de títulos para el Dropdown
                selectedTitle = availableTitles.find { it.idLookupLine == foundCourse.lookupTitle?.idLookupLine }
                    ?: foundCourse.lookupTitle
            } else {
                println("DEBUG: No se encontró el curso con ID $courseId en la lista de ${courses.size} elementos")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Curso") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            try {
                                // 1. Llamada al ViewModel
                                viewModel.softDeleteCourse(courseId.toLong())

                                // 2. Esperamos a que el ViewModel procese y cargue la lista
                                delay(800)

                                // 3. Volvemos atrás
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = "Error al eliminar: ${e.message}"
                            }
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                    }
                }
            )
        }
    ) { padding ->
        // Si está cargando y no tenemos datos aún, mostramos un indicador
        if (isLoading && description.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Selector de Título (LookupLine)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedTitle?.lineName ?: "Selecciona un título",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Título del Curso") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        availableTitles.forEach { title ->
                            DropdownMenuItem(
                                text = { Text(title.lineName) },
                                onClick = {
                                    selectedTitle = title
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción del Curso") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )

                if (errorMessage != null) {
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        selectedTitle?.let { title ->
                            scope.launch {
                                try {
                                    // Actualizamos
                                    viewModel.updateCourse(courseId.toLong(), title.idLookupLine, description)

                                    // Delay para asegurar que el loadCourses() del VM impacte
                                    delay(800)

                                    navController.popBackStack()
                                } catch (e: Exception) {
                                    errorMessage = "Error: ${e.message}"
                                }
                            }
                        } ?: run {
                            errorMessage = "Debes seleccionar un título"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading // Desactivar mientras carga
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Guardar Cambios")
                    }
                }
            }
        }
    }
}