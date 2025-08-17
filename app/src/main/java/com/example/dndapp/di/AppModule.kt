package com.example.dndapp.di

import android.content.Context
import com.example.dndapp.data.database.AppDatabase
import com.example.dndapp.data.database.CharacterDao
import com.example.dndapp.data.database.SpellDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }
    
    @Provides
    fun provideCharacterDao(database: AppDatabase): CharacterDao {
        return database.characterDao()
    }
    
    @Provides
    fun provideSpellDao(database: AppDatabase): SpellDao {
        return database.spellDao()
    }
}

