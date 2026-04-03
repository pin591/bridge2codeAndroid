package com.example.code2bridge_app.ui.screens.course

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.code2bridge_app.data.model.LookupLine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCourseDialog(
    availableTitles: List<LookupLine>,
    onDismiss: () -> Unit,
    onCreate: (lookupTitleId: Int, description: String) -> Unit
) {
    var description by remember { mutableStateOf("") }
    var selectedTitle by remember { mutableStateOf<LookupLine?>(null) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Curso") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                    minLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    selectedTitle?.let { title ->
                        onCreate(title.idLookupLine, description)
                    }
                },
                enabled = selectedTitle != null && description.isNotBlank()
            ) {
                Text("Crear Curso")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}