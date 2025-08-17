package com.example.dndapp.data.repository

import com.example.dndapp.data.database.CharacterDao
import com.example.dndapp.data.models.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) {
    
    fun getAllCharacters(): Flow<List<Character>> = characterDao.getAllCharacters()
    
    suspend fun getCharacterById(id: String): Character? = characterDao.getCharacterById(id)
    
    suspend fun insertCharacter(character: Character) = characterDao.insertCharacter(character)
    
    suspend fun updateCharacter(character: Character) = characterDao.updateCharacter(character)
    
    suspend fun deleteCharacter(character: Character) = characterDao.deleteCharacter(character)
    
    suspend fun deleteCharacterById(id: String) = characterDao.deleteCharacterById(id)
    
    fun searchCharacters(searchQuery: String): Flow<List<Character>> = 
        characterDao.searchCharacters(searchQuery)
}

