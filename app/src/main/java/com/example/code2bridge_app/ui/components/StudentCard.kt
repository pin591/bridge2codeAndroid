package com.example.code2bridge_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.code2bridge_app.data.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCard(student: Student, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${student.name} ${student.surname}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Edad: ${student.age} años",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                // Badge de estado (Activo/Inactivo)
                StatusBadge(enabled = student.enableFlag == "Y")
            }

            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.5f))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                UserInfoRow(icon = Icons.Default.Info, text = "Usuario: ${student.user.username}")
                student.startDate?.let {
                    UserInfoRow(icon = Icons.Default.DateRange, text = it)
                }
            }
        }
    }
}

@Composable
fun StatusBadge(enabled: Boolean) {
    Surface(
        color = if (enabled) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
        contentColor = if (enabled) Color(0xFF2E7D32) else Color(0xFFC62828),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text = if (enabled) "ACTIVO" else "INACTIVO",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold
        )
    }
}