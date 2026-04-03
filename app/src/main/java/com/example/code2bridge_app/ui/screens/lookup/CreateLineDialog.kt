package com.example.code2bridge_app.ui.screens.lookup

import androidx.compose.foundation.gestures.forEach
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.code2bridge_app.data.model.LookupHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLineDialog(
    headers: List<LookupHeader>,
    onDismiss: () -> Unit,
    onCreate: (lineCode: String, lineName: String, lineDescription: String, lookupHeaderId: Int) -> Unit
) {
    var lineCode by remember { mutableStateOf("") }
    var lineName by remember { mutableStateOf("") }
    var lineDescription by remember { mutableStateOf("") }
    var lookupHeaderId by remember { mutableStateOf("") }

    // variables de estado para el select list
    var expanded by remember { mutableStateOf(false) }
    var selectedHeader by remember { mutableStateOf<LookupHeader?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Línea") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = lineCode,
                    onValueChange = { lineCode = it },
                    label = { Text("Código de Línea") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = lineName,
                    onValueChange = { lineName = it },
                    label = { Text("Nombre de Línea") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = lineDescription,
                    onValueChange = { lineDescription = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Aqui pondremos un select list, en este caso un ExposedDropdownMenuBox
                Text(text = "Cabecera", style = MaterialTheme.typography.labelMedium)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedHeader?.headerName ?: "Seleccione una cabecera",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                        modifier = Modifier
                            .menuAnchor() // IMPORTANTE: Ancla el menú al TextField
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        headers.forEach { header ->
                            DropdownMenuItem(
                                text = { Text(header.headerName) },
                                onClick = {
                                    selectedHeader = header
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val headerId = lookupHeaderId.toIntOrNull()
                    if (lineCode.isNotBlank() && lineName.isNotBlank() && selectedHeader != null) {
                        onCreate(lineCode, lineName, lineDescription, selectedHeader!!.idLookupHeader)
                        onDismiss()
                    }
                }
            ) {
                Text("Crear")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}