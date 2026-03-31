package com.code2bridge.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.AuthApi
import com.example.code2bridge_app.data.remote.dto.RegisterRequestDto
import com.example.code2bridge_app.data.remote.dto.StudentCreateDto
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    // Datos del formulario
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAdmin by remember { mutableStateOf(false) }

    // Otras variables de uso en la UI
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 48.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de Estudiante",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Datos del Estudiante
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

        // Datos de Usuario
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Es administrador?")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isAdmin,
                onCheckedChange = { isAdmin = it }
            )
        }

        if (errorMessage != null) {
            Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
        }

        if (successMessage != null) {
            Text(text = successMessage!!, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isBlank() || surname.isBlank() || age.isBlank() ||
                    email.isBlank() || username.isBlank() || password.isBlank()) {
                    errorMessage = "Todos los campos son obligatorios"
                    return@Button
                }

                isLoading = true
                errorMessage = null
                successMessage = null

                scope.launch {
                    try {
                        val authApi = RetrofitClient.instance.create(AuthApi::class.java)

                        // 1. Crear el User
                        val registerDto = RegisterRequestDto(
                            username = username,
                            password = password,
                            email = email,
                            isAdmin = isAdmin,
                            name = name,
                            surname = surname,
                            age = age.toIntOrNull() ?: 0
                        )

                        val response = authApi.register(registerDto)

                        if (response.isSuccessful && response.body()?.success == true) {
                            val userId = response.body()?.idUser
                            val studentId = response.body()?.idStudent
                            successMessage = "¡Registro completado con éxito!"

                            kotlinx.coroutines.delay(1500)
                            navController.navigate("login") {
                                popUpTo("register") { inclusive = true }
                            }
                        } else {
                            errorMessage = response.body()?.message ?: "Error al registrar usuario"
                        }
                    } catch (e: Exception) {
                        errorMessage = "Error de conexión: ${e.message}"
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Registrarse")
            }
        }

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a Iniciar Sesión")
        }
    }
}