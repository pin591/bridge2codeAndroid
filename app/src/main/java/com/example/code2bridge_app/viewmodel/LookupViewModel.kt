package com.example.code2bridge_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code2bridge_app.data.model.LookupHeader
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.data.remote.dto.LookupHeaderCreateDto
import com.example.code2bridge_app.data.remote.dto.LookupLineCreateDto
import com.example.code2bridge_app.data.repository.LookupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LookupViewModel : ViewModel() {
    private val repository = LookupRepository()

    private val _headers = MutableStateFlow<List<LookupHeader>>(emptyList())
    val headers: StateFlow<List<LookupHeader>> = _headers.asStateFlow()

    private val _lines = MutableStateFlow<List<LookupLine>>(emptyList())
    val lines: StateFlow<List<LookupLine>> = _lines.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    fun loadHeaders() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _headers.value = repository.getAllHeaders()
            } catch (e: Exception) {
                _error.value = "Error al cargar cabeceras: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadLines() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _lines.value = repository.getAllLines()
            } catch (e: Exception) {
                _error.value = "Error al cargar líneas: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createHeader(code: String, name: String, description: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val dto = LookupHeaderCreateDto(
                    headerCode = code,
                    headerName = name,
                    headerDescription = description
                )

                repository.createHeader(dto)
                _successMessage.value = "Cabecera creada correctamente"

                // Recargar la lista después de crear
                loadHeaders()

            } catch (e: Exception) {
                _error.value = "Error al crear cabecera: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createLine(
        code: String,
        name: String,
        description: String,
        headerId: Int
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val dto = LookupLineCreateDto(
                    lineCode = code,
                    lineName = name,
                    lineDescription = description,
                    lookupHeaderId = headerId
                )

                repository.createLine(dto)
                _successMessage.value = "Línea creada correctamente"

                // Recargar la lista después de crear
                loadLines()

            } catch (e: Exception) {
                _error.value = "Error al crear línea: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Limpiar mensajes de éxito después de un tiempo
    fun clearSuccessMessage() {
        _successMessage.value = null
    }
}