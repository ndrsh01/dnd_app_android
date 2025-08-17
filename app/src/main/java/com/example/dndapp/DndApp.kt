package com.example.dndapp

import android.app.Application
import com.example.dndapp.data.repository.DataLoader
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class DndApp : Application() {
    
    @Inject
    lateinit var dataLoader: DataLoader
    
    override fun onCreate() {
        super.onCreate()
        
        // Загружаем данные при первом запуске
        CoroutineScope(Dispatchers.IO).launch {
            dataLoader.loadSpellsFromAssets()
            dataLoader.loadBackgroundsFromAssets()
            dataLoader.loadFeatsFromAssets()
        }
    }
}
