package com.example.dndapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.dndapp.data.converters.Converters
import com.example.dndapp.data.models.Character
import com.example.dndapp.data.models.Spell

@Database(
    entities = [Character::class, Spell::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun characterDao(): CharacterDao
    abstract fun spellDao(): SpellDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dnd_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

