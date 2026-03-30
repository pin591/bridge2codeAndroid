package com.example.code2bridge_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code2bridge_app.data.remote.dto.StudentDto
import com.example.code2bridge_app.data.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()
    private val _students = MutableStateFlow<List<StudentDto>>(emptyList())
    val students: StateFlow<List<StudentDto>> = _students.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _students.value = repository.getAll()
            } catch (e: Exception) {
                // TODO: manejar error (Snackbar o Toast)
            } finally {
                _isLoading.value = false
            }
        }
    }
}