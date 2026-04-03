package com.example.code2bridge_app.ui.screens.lookup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.code2bridge_app.data.model.LookupHeader
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.ui.components.LookupHeaderCard
import com.example.code2bridge_app.ui.components.LookupLineCard
import com.example.code2bridge_app.viewmodel.LookupViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookupScreen(viewModel: LookupViewModel = viewModel()) {

    val headers by viewModel.headers.collectAsState()
    val lines by viewModel.lines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    val tabTitles = listOf("Cabeceras", "Líneas")
    var showCreateHeaderDialog by remember { mutableStateOf(false) }
    var showCreateLineDialog by remember { mutableStateOf(false) }

    // Cargar datos al entrar
    LaunchedEffect(Unit) {
        viewModel.loadHeaders()
        viewModel.loadLines()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (pagerState.currentPage == 0) {
                        showCreateHeaderDialog = true
                    } else {
                        showCreateLineDialog = true
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Crear")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {

            // Pestañas de arriba de la pantalla
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(title) }
                    )
                }
            }

            // Contenido de las pestañas
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> LookupHeadersContent(
                        headers = headers,
                        isLoading = isLoading,
                        error = error,
                        viewModel = viewModel
                    )
                    1 -> LookupLinesContent(
                        lines = lines,
                        isLoading = isLoading,
                        error = error,
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    // Modal dialogos de creación
    if (showCreateHeaderDialog) {
        CreateHeaderDialog(
            onDismiss = { showCreateHeaderDialog = false },
            onCreate = { code, name, desc ->
                viewModel.createHeader(code, name, desc)
                println("Crear Header: $code - $name - $desc")
            }
        )
    }

    if (showCreateLineDialog) {
        CreateLineDialog(
            headers = headers,
            onDismiss = { showCreateLineDialog = false },
            onCreate = { code, name, desc, headerId ->
                viewModel.createLine(code, name, desc, headerId)
                println("Crear Line: $code - $name - $desc - HeaderID: $headerId")
            }
        )
    }
}

// Contenido de Cabeceras
@Composable
private fun LookupHeadersContent(
    headers: List<LookupHeader>,
    isLoading: Boolean,
    error: String?,
    viewModel: LookupViewModel
) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (error != null) {
        Text(text = error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(headers) { header ->
                LookupHeaderCard(
                    header = header,
                    onDelete = { viewModel.softDeleteHeader(header.idLookupHeader) }
                )
            }
        }
    }
}

// Contenido de Líneas
@Composable
private fun LookupLinesContent(
    lines: List<LookupLine>,
    isLoading: Boolean,
    error: String?,
    viewModel: LookupViewModel
) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (error != null) {
        Text(text = error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(lines) { line ->
                LookupLineCard(
                    line = line,
                    onDelete = { viewModel.softDeleteLine(line.idLookupLine) }
                )
            }
        }
    }
}