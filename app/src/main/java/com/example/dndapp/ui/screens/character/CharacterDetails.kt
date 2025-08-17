package com.example.dndapp.ui.screens.character

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dndapp.R
import com.example.dndapp.data.models.Character
import com.example.dndapp.ui.viewmodels.CharacterViewModel

@Composable
fun CharacterDetails(
    character: Character,
    viewModel: CharacterViewModel
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Основная информация
            Text(
                text = "Основная информация",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            InfoRow("Имя", character.name.ifEmpty { "Не указано" })
            InfoRow("Класс", character.characterClass.ifEmpty { "Не указано" })
            InfoRow("Раса", character.race.ifEmpty { "Не указано" })
            InfoRow("Уровень", character.level.toString())
            InfoRow("Фон", character.background.ifEmpty { "Не указан" })
            InfoRow("Мировоззрение", character.alignment.ifEmpty { "Не указано" })
            
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    InfoRow("КЗ", character.armorClass.toString())
                    InfoRow("Инициатива", "${character.initiative:+d}")
                    InfoRow("Скорость", "${character.effectiveSpeed} футов")
                }
                Column(modifier = Modifier.weight(1f)) {
                    InfoRow("Макс. HP", character.maxHitPoints.toString())
                    InfoRow("Текущие HP", character.currentHitPoints.toString())
                    InfoRow("Временные HP", character.temporaryHitPoints.toString())
                }
            }
            
            // Управление HP
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { 
                        viewModel.updateCharacterHitPoints(character, character.currentHitPoints - 1)
                    },
                    enabled = character.currentHitPoints > 0
                ) {
                    Text("-1 HP")
                }
                Button(
                    onClick = { 
                        viewModel.updateCharacterHitPoints(character, character.currentHitPoints + 1)
                    },
                    enabled = character.currentHitPoints < character.maxHitPoints
                ) {
                    Text("+1 HP")
                }
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    AbilityRow("СИЛ", character.strength, character.strengthModifier)
                    AbilityRow("ЛОВ", character.dexterity, character.dexterityModifier)
                    AbilityRow("ТЕЛ", character.constitution, character.constitutionModifier)
                }
                Column(modifier = Modifier.weight(1f)) {
                    AbilityRow("ИНТ", character.intelligence, character.intelligenceModifier)
                    AbilityRow("МДР", character.wisdom, character.wisdomModifier)
                    AbilityRow("ХАР", character.charisma, character.charismaModifier)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Истощение
            Text(
                text = "Истощение",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Уровень: ${character.exhaustionLevel}")
                Button(
                    onClick = { 
                        viewModel.updateCharacterExhaustion(character, character.exhaustionLevel - 1)
                    },
                    enabled = character.exhaustionLevel > 0
                ) {
                    Text("-1")
                }
                Button(
                    onClick = { 
                        viewModel.updateCharacterExhaustion(character, character.exhaustionLevel + 1)
                    },
                    enabled = character.exhaustionLevel < 6
                ) {
                    Text("+1")
                }
            }
        }
    }
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

@Composable
fun AbilityRow(abbreviation: String, score: Int, modifier: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = abbreviation,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            text = "$score (${modifier:+d})",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

