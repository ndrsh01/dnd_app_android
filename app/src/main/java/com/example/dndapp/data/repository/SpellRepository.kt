package com.example.dndapp.data.repository

import com.example.dndapp.data.database.SpellDao
import com.example.dndapp.data.models.Spell
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpellRepository @Inject constructor(
    private val spellDao: SpellDao
) {
    
    fun getAllSpells(): Flow<List<Spell>> = spellDao.getAllSpells()
    
    suspend fun getSpellById(id: String): Spell? = spellDao.getSpellById(id)
    
    fun searchSpells(searchQuery: String): Flow<List<Spell>> = spellDao.searchSpells(searchQuery)
    
    fun getSpellsByLevel(level: Int): Flow<List<Spell>> = spellDao.getSpellsByLevel(level)
    
    fun getSpellsBySchool(school: String): Flow<List<Spell>> = spellDao.getSpellsBySchool(school)
    
    fun getSpellsByClass(className: String): Flow<List<Spell>> = spellDao.getSpellsByClass(className)
    
    fun getSpellsByConcentration(concentration: Boolean): Flow<List<Spell>> = 
        spellDao.getSpellsByConcentration(concentration)
    
    fun getSpellsByRitual(ritual: Boolean): Flow<List<Spell>> = spellDao.getSpellsByRitual(ritual)
    
    suspend fun insertSpell(spell: Spell) = spellDao.insertSpell(spell)
    
    suspend fun insertSpells(spells: List<Spell>) = spellDao.insertSpells(spells)
    
    suspend fun updateSpell(spell: Spell) = spellDao.updateSpell(spell)
    
    suspend fun deleteSpell(spell: Spell) = spellDao.deleteSpell(spell)
    
    suspend fun deleteAllSpells() = spellDao.deleteAllSpells()
}

