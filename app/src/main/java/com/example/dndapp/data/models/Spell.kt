package com.example.dndapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "spells")
data class Spell(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    
    @SerializedName("Название")
    val name: String,
    
    @SerializedName("Уровень")
    val level: Int,
    
    @SerializedName("Школа")
    val school: String,
    
    @SerializedName("Классы")
    val classes: List<String>,
    
    @SerializedName("Подклассы")
    val subclasses: List<String>,
    
    @SerializedName("Концентрация")
    val concentration: Boolean,
    
    @SerializedName("Ритуал")
    val ritual: Boolean,
    
    @SerializedName("Время сотворения")
    val castingTime: String,
    
    @SerializedName("Дистанция")
    val range: String,
    
    @SerializedName("Компоненты")
    val components: String,
    
    @SerializedName("Длительность")
    val duration: String,
    
    @SerializedName("Описание")
    val description: String,
    
    @SerializedName("Улучшения")
    val improvements: String = ""
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Spell {
            return Spell(
                name = json["Название"] as? String ?: "",
                level = (json["Уровень"] as? String)?.toIntOrNull() ?: 0,
                school = json["Школа"] as? String ?: "",
                classes = (json["Классы"] as? String)?.split(", ")?.filter { it.isNotEmpty() } ?: emptyList(),
                subclasses = (json["Подклассы"] as? String)?.split(", ")?.filter { it.isNotEmpty() } ?: emptyList(),
                concentration = json["Концентрация"] as? Boolean ?: false,
                ritual = json["Ритуал"] as? Boolean ?: false,
                castingTime = json["Время сотворения"] as? String ?: "",
                range = json["Дистанция"] as? String ?: "",
                components = json["Компоненты"] as? String ?: "",
                duration = json["Длительность"] as? String ?: "",
                description = json["Описание"] as? String ?: "",
                improvements = json["Улучшения"] as? String ?: ""
            )
        }
    }
}

