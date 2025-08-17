package com.example.dndapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndapp.data.models.Character
import com.example.dndapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()
    
    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadCharacters()
    }
    
    private fun loadCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            characterRepository.getAllCharacters().collect { characters ->
                _characters.value = characters
                _isLoading.value = false
                
                // Если нет выбранного персонажа и есть персонажи, выбираем первого
                if (_selectedCharacter.value == null && characters.isNotEmpty()) {
                    _selectedCharacter.value = characters.first()
                }
            }
        }
    }
    
    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
    }
    
    fun addCharacter(character: Character) {
        viewModelScope.launch {
            characterRepository.insertCharacter(character)
        }
    }
    
    fun updateCharacter(character: Character) {
        viewModelScope.launch {
            characterRepository.updateCharacter(character)
        }
    }
    
    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            characterRepository.deleteCharacter(character)
            if (_selectedCharacter.value?.id == character.id) {
                _selectedCharacter.value = _characters.value.firstOrNull()
            }
        }
    }
    
    fun updateCharacterHitPoints(character: Character, newCurrentHP: Int) {
        val updatedCharacter = character.copy(
            currentHitPoints = maxOf(0, minOf(newCurrentHP, character.maxHitPoints))
        )
        updateCharacter(updatedCharacter)
    }
    
    fun updateCharacterExhaustion(character: Character, newExhaustionLevel: Int) {
        val updatedCharacter = character.copy(
            exhaustionLevel = maxOf(0, minOf(newExhaustionLevel, 6))
        )
        updateCharacter(updatedCharacter)
    }
}

