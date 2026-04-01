package com.example.code2bridge_app.ui.screens.lookup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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

@Composable
fun CreateLineDialog(
    onDismiss: () -> Unit,
    onCreate: (lineCode: String, lineName: String, lineDescription: String, lookupHeaderId: Int) -> Unit
) {
    var lineCode by remember { mutableStateOf("") }
    var lineName by remember { mutableStateOf("") }
    var lineDescription by remember { mutableStateOf("") }
    var lookupHeaderId by remember { mutableStateOf("") }

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
                OutlinedTextField(
                    value = lookupHeaderId,
                    onValueChange = { lookupHeaderId = it },
                    label = { Text("ID de Cabecera") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val headerId = lookupHeaderId.toIntOrNull()
                    if (lineCode.isNotBlank() && lineName.isNotBlank() && headerId != null) {
                        onCreate(lineCode, lineName, lineDescription, headerId)
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