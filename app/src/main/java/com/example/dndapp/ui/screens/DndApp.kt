package com.example.dndapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dndapp.R
import com.example.dndapp.ui.screens.character.CharacterScreen
import com.example.dndapp.ui.screens.compendium.CompendiumScreen
import com.example.dndapp.ui.screens.notes.NotesScreen
import com.example.dndapp.ui.screens.quotes.QuotesScreen
import com.example.dndapp.ui.screens.relationships.RelationshipsScreen

sealed class Screen(val route: String, val titleResId: Int, val icon: ImageVector) {
    object Quotes : Screen("quotes", R.string.nav_quotes, Icons.Default.Chat)
    object Relationships : Screen("relationships", R.string.nav_relationships, Icons.Default.Favorite)
    object Compendium : Screen("compendium", R.string.nav_compendium, Icons.Default.Book)
    object Notes : Screen("notes", R.string.nav_notes, Icons.Default.Note)
    object Character : Screen("character", R.string.nav_character, Icons.Default.Person)
}

val screens = listOf(
    Screen.Quotes,
    Screen.Relationships,
    Screen.Compendium,
    Screen.Notes,
    Screen.Character
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DndApp() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.titleResId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Quotes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Quotes.route) {
                QuotesScreen()
            }
            composable(Screen.Relationships.route) {
                RelationshipsScreen()
            }
            composable(Screen.Compendium.route) {
                CompendiumScreen()
            }
            composable(Screen.Notes.route) {
                NotesScreen()
            }
            composable(Screen.Character.route) {
                CharacterScreen()
            }
        }
    }
}

