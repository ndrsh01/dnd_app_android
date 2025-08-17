package com.example.dndapp.data.database

import androidx.room.*
import com.example.dndapp.data.models.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    
    @Query("SELECT * FROM characters ORDER BY dateModified DESC")
    fun getAllCharacters(): Flow<List<Character>>
    
    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: String): Character?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)
    
    @Update
    suspend fun updateCharacter(character: Character)
    
    @Delete
    suspend fun deleteCharacter(character: Character)
    
    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacterById(id: String)
    
    @Query("SELECT * FROM characters WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchCharacters(searchQuery: String): Flow<List<Character>>
}

