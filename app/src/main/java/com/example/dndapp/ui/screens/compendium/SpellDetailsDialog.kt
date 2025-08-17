package com.example.dndapp.ui.screens.compendium

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dndapp.data.models.Spell

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpellDetailsDialog(
    spell: Spell,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = spell.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Основная информация
                InfoRow("Уровень", spell.level.toString())
                InfoRow("Школа", spell.school)
                InfoRow("Время сотворения", spell.castingTime)
                InfoRow("Дистанция", spell.range)
                InfoRow("Компоненты", spell.components)
                InfoRow("Длительность", spell.duration)
                
                if (spell.concentration) {
                    InfoRow("Концентрация", "Да")
                }
                if (spell.ritual) {
                    InfoRow("Ритуал", "Да")
                }
                
                if (spell.classes.isNotEmpty()) {
                    InfoRow("Классы", spell.classes.joinToString(", "))
                }
                
                if (spell.subclasses.isNotEmpty()) {
                    InfoRow("Подклассы", spell.subclasses.joinToString(", "))
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Описание
                Text(
                    text = "Описание",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = spell.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                
                if (spell.improvements.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Улучшения",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = spell.improvements,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Закрыть")
            }
        }
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

