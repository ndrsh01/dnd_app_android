package com.example.dndapp.ui.screens.compendium

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dndapp.R
import com.example.dndapp.ui.viewmodels.SpellViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    viewModel: SpellViewModel,
    onDismiss: () -> Unit
) {
    val levels = viewModel.getUniqueLevels()
    val schools = viewModel.getUniqueSchools()
    val classes = viewModel.getUniqueClasses()
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Фильтры",
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
                // Уровни
                Text(
                    text = "Уровни заклинаний",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    levels.forEach { level ->
                        FilterChip(
                            selected = false, // TODO: Implement level filter state
                            onClick = { viewModel.toggleLevelFilter(level) },
                            label = { Text(level.toString()) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Школы
                Text(
                    text = "Школы магии",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                schools.forEach { school ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(school)
                        Checkbox(
                            checked = false, // TODO: Implement school filter state
                            onCheckedChange = { viewModel.toggleSchoolFilter(school) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Классы
                Text(
                    text = "Классы персонажей",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                classes.forEach { className ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(className)
                        Checkbox(
                            checked = false, // TODO: Implement class filter state
                            onCheckedChange = { viewModel.toggleClassFilter(className) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Специальные фильтры
                Text(
                    text = "Специальные фильтры",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Только концентрация")
                    Checkbox(
                        checked = false, // TODO: Implement concentration filter state
                        onCheckedChange = { viewModel.setConcentrationOnly(it) }
                    )
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Только ритуалы")
                    Checkbox(
                        checked = false, // TODO: Implement ritual filter state
                        onCheckedChange = { viewModel.setRitualOnly(it) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.clearFilters()
                    onDismiss()
                }
            ) {
                Text("Очистить фильтры")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}
