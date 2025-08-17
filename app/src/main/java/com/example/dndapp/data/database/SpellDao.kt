package com.example.dndapp.data.database

import androidx.room.*
import com.example.dndapp.data.models.Spell
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    
    @Query("SELECT * FROM spells ORDER BY level ASC, name ASC")
    fun getAllSpells(): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE id = :id")
    suspend fun getSpellById(id: String): Spell?
    
    @Query("SELECT * FROM spells WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchSpells(searchQuery: String): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE level = :level")
    fun getSpellsByLevel(level: Int): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE school = :school")
    fun getSpellsBySchool(school: String): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE :className IN (SELECT value FROM json_each(classes))")
    fun getSpellsByClass(className: String): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE concentration = :concentration")
    fun getSpellsByConcentration(concentration: Boolean): Flow<List<Spell>>
    
    @Query("SELECT * FROM spells WHERE ritual = :ritual")
    fun getSpellsByRitual(ritual: Boolean): Flow<List<Spell>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpell(spell: Spell)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpells(spells: List<Spell>)
    
    @Update
    suspend fun updateSpell(spell: Spell)
    
    @Delete
    suspend fun deleteSpell(spell: Spell)
    
    @Query("DELETE FROM spells")
    suspend fun deleteAllSpells()
}

