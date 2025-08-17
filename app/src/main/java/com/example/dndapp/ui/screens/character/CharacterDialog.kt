package com.example.dndapp.ui.screens.character

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
import com.example.dndapp.data.models.Character
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDialog(
    character: Character?,
    onDismiss: () -> Unit,
    onSave: (Character) -> Unit
) {
    var name by remember { mutableStateOf(character?.name ?: "") }
    var characterClass by remember { mutableStateOf(character?.characterClass ?: "") }
    var race by remember { mutableStateOf(character?.race ?: "") }
    var level by remember { mutableStateOf(character?.level?.toString() ?: "1") }
    var background by remember { mutableStateOf(character?.background ?: "") }
    var alignment by remember { mutableStateOf(character?.alignment ?: "") }
    var maxHitPoints by remember { mutableStateOf(character?.maxHitPoints?.toString() ?: "0") }
    var armorClass by remember { mutableStateOf(character?.armorClass?.toString() ?: "10") }
    var strength by remember { mutableStateOf(character?.strength?.toString() ?: "10") }
    var dexterity by remember { mutableStateOf(character?.dexterity?.toString() ?: "10") }
    var constitution by remember { mutableStateOf(character?.constitution?.toString() ?: "10") }
    var intelligence by remember { mutableStateOf(character?.intelligence?.toString() ?: "10") }
    var wisdom by remember { mutableStateOf(character?.wisdom?.toString() ?: "10") }
    var charisma by remember { mutableStateOf(character?.charisma?.toString() ?: "10") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (character == null) "Создать персонажа" else "Редактировать персонажа",
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
                Text(
                    text = "Основная информация",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Имя") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = characterClass,
                    onValueChange = { characterClass = it },
                    label = { Text("Класс") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = race,
                    onValueChange = { race = it },
                    label = { Text("Раса") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = level,
                    onValueChange = { level = it },
                    label = { Text("Уровень") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = background,
                    onValueChange = { background = it },
                    label = { Text("Фон") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = alignment,
                    onValueChange = { alignment = it },
                    label = { Text("Мировоззрение") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Боевые характеристики
                Text(
                    text = "Боевые характеристики",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = maxHitPoints,
                        onValueChange = { maxHitPoints = it },
                        label = { Text("Макс. HP") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = armorClass,
                        onValueChange = { armorClass = it },
                        label = { Text("КЗ") },
                        modifier = Modifier.weight(1f)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Характеристики
                Text(
                    text = "Характеристики",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = strength,
                        onValueChange = { strength = it },
                        label = { Text("СИЛ") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = dexterity,
                        onValueChange = { dexterity = it },
                        label = { Text("ЛОВ") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = constitution,
                        onValueChange = { constitution = it },
                        label = { Text("ТЕЛ") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = intelligence,
                        onValueChange = { intelligence = it },
                        label = { Text("ИНТ") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = wisdom,
                        onValueChange = { wisdom = it },
                        label = { Text("МДР") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = charisma,
                        onValueChange = { charisma = it },
                        label = { Text("ХАР") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newCharacter = Character(
                        id = character?.id ?: UUID.randomUUID().toString(),
                        name = name,
                        characterClass = characterClass,
                        race = race,
                        level = level.toIntOrNull() ?: 1,
                        background = background,
                        alignment = alignment,
                        maxHitPoints = maxHitPoints.toIntOrNull() ?: 0,
                        currentHitPoints = maxHitPoints.toIntOrNull() ?: 0,
                        armorClass = armorClass.toIntOrNull() ?: 10,
                        strength = strength.toIntOrNull() ?: 10,
                        dexterity = dexterity.toIntOrNull() ?: 10,
                        constitution = constitution.toIntOrNull() ?: 10,
                        intelligence = intelligence.toIntOrNull() ?: 10,
                        wisdom = wisdom.toIntOrNull() ?: 10,
                        charisma = charisma.toIntOrNull() ?: 10,
                        dateCreated = character?.dateCreated ?: Date(),
                        dateModified = Date()
                    )
                    onSave(newCharacter)
                }
            ) {
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

