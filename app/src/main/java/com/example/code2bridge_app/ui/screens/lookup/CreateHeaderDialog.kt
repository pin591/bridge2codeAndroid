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
fun CreateHeaderDialog(
    onDismiss: () -> Unit,
    onCreate: (headerCode: String, headerName: String, headerDescription: String) -> Unit
) {
    var headerCode by remember { mutableStateOf("") }
    var headerName by remember { mutableStateOf("") }
    var headerDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Cabecera") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = headerCode,
                    onValueChange = { headerCode = it },
                    label = { Text("Código") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = headerName,
                    onValueChange = { headerName = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = headerDescription,
                    onValueChange = { headerDescription = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (headerCode.isNotBlank() && headerName.isNotBlank()) {
                        onCreate(headerCode, headerName, headerDescription)
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