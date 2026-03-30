package com.example.code2bridge_app.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.code2bridge_app.data.model.User
import com.example.code2bridge_app.ui.components.UserCard
import com.example.code2bridge_app.viewmodel.UserViewModel

@Preview(showBackground = true, name = "Vista previa pantalla usuarios")
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users = viewModel.users

    LaunchedEffect(true) {
        viewModel.loadUsers()
    }

    UserListContent(users = users)
}

@Composable
fun UserListContent(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            UserCard(user)
        }
    }
}

@Preview(showBackground = true, name = "Vista previa con datos")
@Composable
fun UserScreenPreview() {
    val mockUsers = listOf(
        User(
            idUser = 1,
            username = "Prueba 1",
            email = "test@mail.com",
            isAdmin = true,
            enableFlag = "Y",
            startDate = "2024-01-01",
            endDate = null
        )
    )
    UserListContent(users = mockUsers)
}
