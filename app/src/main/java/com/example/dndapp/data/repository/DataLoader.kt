package com.example.dndapp.data.repository

import android.content.Context
import com.example.dndapp.data.models.Spell
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataLoader @Inject constructor(
    private val context: Context,
    private val spellRepository: SpellRepository
) {
    
    private val gson = Gson()
    
    suspend fun loadSpellsFromAssets() = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("spells.json").bufferedReader().use { it.readText() }
            val spellsList = gson.fromJson<List<Map<String, Any>>>(jsonString, object : TypeToken<List<Map<String, Any>>>() {}.type)
            
            val spells = spellsList.map { jsonMap ->
                Spell.fromJson(jsonMap)
            }
            
            spellRepository.insertSpells(spells)
            println("✅ Загружено ${spells.size} заклинаний")
        } catch (e: Exception) {
            println("❌ Ошибка загрузки заклинаний: ${e.message}")
        }
    }
    
    suspend fun loadBackgroundsFromAssets() = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("backgrounds.json").bufferedReader().use { it.readText() }
            // TODO: Implement background loading
            println("✅ Загружены предыстории")
        } catch (e: Exception) {
            println("❌ Ошибка загрузки предысторий: ${e.message}")
        }
    }
    
    suspend fun loadFeatsFromAssets() = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("feats.json").bufferedReader().use { it.readText() }
            // TODO: Implement feats loading
            println("✅ Загружены черты")
        } catch (e: Exception) {
            println("❌ Ошибка загрузки черт: ${e.message}")
        }
    }
}

