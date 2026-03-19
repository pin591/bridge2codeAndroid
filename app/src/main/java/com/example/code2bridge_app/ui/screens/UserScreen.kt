package com.example.code2bridge_app.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.code2bridge_app.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users = viewModel.users

    LaunchedEffect(true) {
        viewModel.loadUsers()
    }

    LazyColumn {
        items(users) { user ->
            Text(text = user.username)
        }
    }
}
