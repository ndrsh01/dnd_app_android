package com.example.dndapp.data.converters

import androidx.room.TypeConverter
import com.example.dndapp.data.models.Attack
import com.example.dndapp.data.models.CharacterSpell
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    private val gson = Gson()
    
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
    
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
    
    @TypeConverter
    fun fromMap(value: Map<String, Boolean>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toMap(value: String): Map<String, Boolean> {
        val mapType = object : TypeToken<Map<String, Boolean>>() {}.type
        return gson.fromJson(value, mapType)
    }
    
    @TypeConverter
    fun fromIntMap(value: Map<Int, Int>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toIntMap(value: String): Map<Int, Int> {
        val mapType = object : TypeToken<Map<Int, Int>>() {}.type
        return gson.fromJson(value, mapType)
    }
    
    @TypeConverter
    fun fromAttackList(value: List<Attack>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toAttackList(value: String): List<Attack> {
        val listType = object : TypeToken<List<Attack>>() {}.type
        return gson.fromJson(value, listType)
    }
    
    @TypeConverter
    fun fromCharacterSpellList(value: List<CharacterSpell>): String {
        return gson.toJson(value)
    }
    
    @TypeConverter
    fun toCharacterSpellList(value: String): List<CharacterSpell> {
        val listType = object : TypeToken<List<CharacterSpell>>() {}.type
        return gson.fromJson(value, listType)
    }
}
