package com.example.code2bridge_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code2bridge_app.data.model.Course
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.data.remote.dto.CourseCreateDto
import com.example.code2bridge_app.data.remote.dto.CourseUpdateDto
import com.example.code2bridge_app.data.repository.CourseRepository
import com.example.code2bridge_app.data.repository.LookupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {

    private val courseRepository = CourseRepository()
    private val lookupRepository = LookupRepository()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    private val _availableTitles = MutableStateFlow<List<LookupLine>>(emptyList())
    val availableTitles: StateFlow<List<LookupLine>> = _availableTitles.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    init {
        loadCourses()
        loadAvailableTitles()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _courses.value = courseRepository.getAll()
            } catch (e: Exception) {
                _error.value = "Error al cargar cursos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAvailableTitles() {
        viewModelScope.launch {
            try {
                // Cargamos todas las líneas y filtramos por cabecera "TITULOS" (ajusta el código si es diferente)
                _availableTitles.value = lookupRepository.getAllLines()
                    .filter { it.lookupHeader?.headerCode == "TITULOS" }
            } catch (e: Exception) {
                _error.value = "Error al cargar títulos: ${e.message}"
            }
        }
    }

    fun createCourse(lookupTitleId: Int, description: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val dto = CourseCreateDto(lookupTitleId, description)
                courseRepository.create(dto)
                _successMessage.value = "Curso creado correctamente"
                loadCourses()
            } catch (e: Exception) {
                _error.value = "Error al crear curso: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateCourse(id: Long, lookupTitleId: Int, description: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val dto = CourseUpdateDto(lookupTitleId, description)
                courseRepository.update(id, dto)
                _successMessage.value = "Curso actualizado correctamente"
                loadCourses()
            } catch (e: Exception) {
                _error.value = "Error al actualizar curso: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun softDeleteCourse(id: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                courseRepository.softDelete(id)
                _successMessage.value = "Curso eliminado correctamente"
                loadCourses()
            } catch (e: Exception) {
                _error.value = "Error al eliminar curso: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSuccessMessage() {
        _successMessage.value = null
    }
}