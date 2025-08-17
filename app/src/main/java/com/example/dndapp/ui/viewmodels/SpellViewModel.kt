package com.example.dndapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndapp.data.models.Spell
import com.example.dndapp.data.repository.SpellRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellViewModel @Inject constructor(
    private val spellRepository: SpellRepository
) : ViewModel() {
    
    private val _spells = MutableStateFlow<List<Spell>>(emptyList())
    val spells: StateFlow<List<Spell>> = _spells.asStateFlow()
    
    private val _filteredSpells = MutableStateFlow<List<Spell>>(emptyList())
    val filteredSpells: StateFlow<List<Spell>> = _filteredSpells.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _selectedLevels = MutableStateFlow<Set<Int>>(emptySet())
    private val _selectedSchools = MutableStateFlow<Set<String>>(emptySet())
    private val _selectedClasses = MutableStateFlow<Set<String>>(emptySet())
    private val _concentrationOnly = MutableStateFlow(false)
    private val _ritualOnly = MutableStateFlow(false)
    
    init {
        loadSpells()
    }
    
    private fun loadSpells() {
        viewModelScope.launch {
            _isLoading.value = true
            spellRepository.getAllSpells().collect { spells ->
                _spells.value = spells
                _filteredSpells.value = spells
                _isLoading.value = false
            }
        }
    }
    
    fun searchSpells(query: String) {
        _searchQuery.value = query
        applyFilters()
    }
    
    fun toggleLevelFilter(level: Int) {
        val currentLevels = _selectedLevels.value.toMutableSet()
        if (currentLevels.contains(level)) {
            currentLevels.remove(level)
        } else {
            currentLevels.add(level)
        }
        _selectedLevels.value = currentLevels
        applyFilters()
    }
    
    fun toggleSchoolFilter(school: String) {
        val currentSchools = _selectedSchools.value.toMutableSet()
        if (currentSchools.contains(school)) {
            currentSchools.remove(school)
        } else {
            currentSchools.add(school)
        }
        _selectedSchools.value = currentSchools
        applyFilters()
    }
    
    fun toggleClassFilter(className: String) {
        val currentClasses = _selectedClasses.value.toMutableSet()
        if (currentClasses.contains(className)) {
            currentClasses.remove(className)
        } else {
            currentClasses.add(className)
        }
        _selectedClasses.value = currentClasses
        applyFilters()
    }
    
    fun setConcentrationOnly(concentrationOnly: Boolean) {
        _concentrationOnly.value = concentrationOnly
        applyFilters()
    }
    
    fun setRitualOnly(ritualOnly: Boolean) {
        _ritualOnly.value = ritualOnly
        applyFilters()
    }
    
    fun clearFilters() {
        _searchQuery.value = ""
        _selectedLevels.value = emptySet()
        _selectedSchools.value = emptySet()
        _selectedClasses.value = emptySet()
        _concentrationOnly.value = false
        _ritualOnly.value = false
        _filteredSpells.value = _spells.value
    }
    
    private fun applyFilters() {
        var filtered = _spells.value
        
        // Поиск по тексту
        if (_searchQuery.value.isNotEmpty()) {
            filtered = filtered.filter { spell ->
                spell.name.contains(_searchQuery.value, ignoreCase = true) ||
                spell.description.contains(_searchQuery.value, ignoreCase = true)
            }
        }
        
        // Фильтр по уровням
        if (_selectedLevels.value.isNotEmpty()) {
            filtered = filtered.filter { spell ->
                _selectedLevels.value.contains(spell.level)
            }
        }
        
        // Фильтр по школам
        if (_selectedSchools.value.isNotEmpty()) {
            filtered = filtered.filter { spell ->
                _selectedSchools.value.contains(spell.school)
            }
        }
        
        // Фильтр по классам
        if (_selectedClasses.value.isNotEmpty()) {
            filtered = filtered.filter { spell ->
                spell.classes.any { className ->
                    _selectedClasses.value.contains(className)
                }
            }
        }
        
        // Фильтр по концентрации
        if (_concentrationOnly.value) {
            filtered = filtered.filter { it.concentration }
        }
        
        // Фильтр по ритуалам
        if (_ritualOnly.value) {
            filtered = filtered.filter { it.ritual }
        }
        
        _filteredSpells.value = filtered
    }
    
    fun getUniqueSchools(): List<String> {
        return _spells.value.map { it.school }.distinct().sorted()
    }
    
    fun getUniqueClasses(): List<String> {
        return _spells.value.flatMap { it.classes }.distinct().sorted()
    }
    
    fun getUniqueLevels(): List<Int> {
        return _spells.value.map { it.level }.distinct().sorted()
    }
}

