package com.example.dndapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// MARK: - Spell Models
@Parcelize
data class Spell(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val level: Int,
    val school: String,
    val classes: List<String>,
    @SerializedName("actionType")
    val actionType: String? = null,
    val concentration: Boolean,
    val ritual: Boolean,
    @SerializedName("castingTime")
    val castingTime: String? = null,
    val range: String? = null,
    val components: List<String>? = null,
    val duration: String? = null,
    val description: String,
    val material: String? = null,
    @SerializedName("cantripUpgrade")
    val cantripUpgrade: String? = null
) : Parcelable

// MARK: - Feat Models
@Parcelize
data class Feat(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val category: String
) : Parcelable

// MARK: - Quote Models
@Parcelize
data class Quote(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val category: String
) : Parcelable

// MARK: - Note Models
@Parcelize
data class Note(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable

// MARK: - Filter Models
data class SpellFilters(
    var searchText: String = "",
    var selectedLevels: MutableSet<Int> = mutableSetOf(),
    var selectedSchools: MutableSet<String> = mutableSetOf(),
    var selectedClasses: MutableSet<String> = mutableSetOf(),
    var concentrationOnly: Boolean = false,
    var ritualOnly: Boolean = false
) {
    val isActive: Boolean
        get() = searchText.isNotEmpty() || selectedLevels.isNotEmpty() || 
                selectedSchools.isNotEmpty() || selectedClasses.isNotEmpty() || 
                concentrationOnly || ritualOnly

    fun clear() {
        searchText = ""
        selectedLevels.clear()
        selectedSchools.clear()
        selectedClasses.clear()
        concentrationOnly = false
        ritualOnly = false
    }

    fun toggleLevelFilter(level: Int) {
        if (selectedLevels.contains(level)) {
            selectedLevels.remove(level)
        } else {
            selectedLevels.add(level)
        }
    }

    fun toggleSchoolFilter(school: String) {
        if (selectedSchools.contains(school)) {
            selectedSchools.remove(school)
        } else {
            selectedSchools.add(school)
        }
    }

    fun toggleClassFilter(className: String) {
        if (selectedClasses.contains(className)) {
            selectedClasses.remove(className)
        } else {
            selectedClasses.add(className)
        }
    }
}

data class FeatFilters(
    var searchText: String = "",
    var selectedCategories: MutableSet<String> = mutableSetOf()
) {
    val isActive: Boolean
        get() = searchText.isNotEmpty() || selectedCategories.isNotEmpty()

    fun clear() {
        searchText = ""
        selectedCategories.clear()
    }

    fun toggleCategoryFilter(category: String) {
        if (selectedCategories.contains(category)) {
            selectedCategories.remove(category)
        } else {
            selectedCategories.add(category)
        }
    }
}
