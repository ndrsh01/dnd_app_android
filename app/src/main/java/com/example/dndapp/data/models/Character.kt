package com.example.dndapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dndapp.data.converters.Converters
import java.util.Date

@Entity(tableName = "characters")
@TypeConverters(Converters::class)
data class Character(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    
    var name: String = "",
    var playerName: String = "",
    var race: String = "",
    var characterClass: String = "",
    var background: String = "",
    var alignment: String = "",
    var experience: Int = 0,
    var level: Int = 1,
    
    // Combat Stats
    var armorClass: Int = 10,
    var initiative: Int = 0,
    var speed: Int = 30,
    var maxHitPoints: Int = 0,
    var currentHitPoints: Int = 0,
    var temporaryHitPoints: Int = 0,
    var hitDiceTotal: Int = 0,
    var hitDiceType: String = "d6",
    var proficiencyBonus: Int = 2,
    var inspiration: Boolean = false,
    
    // Death Saves
    var deathSaveSuccesses: Int = 0,
    var deathSaveFailures: Int = 0,
    
    // Exhaustion
    var exhaustionLevel: Int = 0,
    
    // Ability Scores
    var strength: Int = 10,
    var dexterity: Int = 10,
    var constitution: Int = 10,
    var intelligence: Int = 10,
    var wisdom: Int = 10,
    var charisma: Int = 10,
    
    // Saving Throws
    var savingThrows: Map<String, Boolean> = mapOf(
        "strength" to false,
        "dexterity" to false,
        "constitution" to false,
        "intelligence" to false,
        "wisdom" to false,
        "charisma" to false
    ),
    
    // Skills
    var skills: Map<String, Boolean> = mapOf(
        "acrobatics" to false,
        "animal_handling" to false,
        "arcana" to false,
        "athletics" to false,
        "deception" to false,
        "history" to false,
        "insight" to false,
        "intimidation" to false,
        "investigation" to false,
        "medicine" to false,
        "nature" to false,
        "perception" to false,
        "performance" to false,
        "persuasion" to false,
        "religion" to false,
        "sleight_of_hand" to false,
        "stealth" to false,
        "survival" to false
    ),
    
    // Character Traits
    var personalityTraits: String = "",
    var ideals: String = "",
    var bonds: String = "",
    var flaws: String = "",
    
    // Equipment and Features
    var equipment: String = "",
    var featuresAndTraits: String = "",
    var otherProficiencies: String = "",
    
    // Attacks and Spellcasting
    var attacks: List<Attack> = emptyList(),
    var spellSlots: Map<Int, Int> = mapOf(1 to 0, 2 to 0, 3 to 0, 4 to 0, 5 to 0),
    
    // Spells
    var spells: List<CharacterSpell> = emptyList(),
    
    // Treasure and Resources
    var treasure: String = "",
    var specialResources: String = "",
    
    // Languages
    var languages: String = "",
    
    // Hit Dice
    var hitDiceUsed: Int = 0,
    
    var dateCreated: Date = Date(),
    var dateModified: Date = Date()
) {
    // Computed Properties
    val strengthModifier: Int get() = (strength - 10) / 2
    val dexterityModifier: Int get() = (dexterity - 10) / 2
    val constitutionModifier: Int get() = (constitution - 10) / 2
    val intelligenceModifier: Int get() = (intelligence - 10) / 2
    val wisdomModifier: Int get() = (wisdom - 10) / 2
    val charismaModifier: Int get() = (charisma - 10) / 2
    
    val passivePerception: Int get() = 10 + wisdomModifier + (if (skills["perception"] == true) proficiencyBonus else 0)
    
    // Скорость с учетом истощения
    val effectiveSpeed: Int get() = maxOf(0, speed - (exhaustionLevel * 5))
    
    // Skill modifiers
    fun skillModifier(skill: String): Int {
        val skillAbilities = mapOf(
            "acrobatics" to "dexterity",
            "animal_handling" to "wisdom",
            "arcana" to "intelligence",
            "athletics" to "strength",
            "deception" to "charisma",
            "history" to "intelligence",
            "insight" to "wisdom",
            "intimidation" to "charisma",
            "investigation" to "intelligence",
            "medicine" to "wisdom",
            "nature" to "intelligence",
            "perception" to "wisdom",
            "performance" to "charisma",
            "persuasion" to "charisma",
            "religion" to "intelligence",
            "sleight_of_hand" to "dexterity",
            "stealth" to "dexterity",
            "survival" to "wisdom"
        )
        
        val ability = skillAbilities[skill] ?: return 0
        
        val abilityModifier = when (ability) {
            "strength" -> strengthModifier
            "dexterity" -> dexterityModifier
            "constitution" -> constitutionModifier
            "intelligence" -> intelligenceModifier
            "wisdom" -> wisdomModifier
            "charisma" -> charismaModifier
            else -> 0
        }
        
        return abilityModifier + (if (skills[skill] == true) proficiencyBonus else 0)
    }
    
    // Saving throw modifiers
    fun savingThrowModifier(ability: String): Int {
        val abilityModifier = when (ability) {
            "strength" -> strengthModifier
            "dexterity" -> dexterityModifier
            "constitution" -> constitutionModifier
            "intelligence" -> intelligenceModifier
            "wisdom" -> wisdomModifier
            "charisma" -> charismaModifier
            else -> 0
        }
        
        return abilityModifier + (if (savingThrows[ability] == true) proficiencyBonus else 0)
    }
}

data class Attack(
    val id: String = java.util.UUID.randomUUID().toString(),
    var name: String = "",
    var attackBonus: String = "",
    var damageType: String = ""
)

data class CharacterSpell(
    val id: String = java.util.UUID.randomUUID().toString(),
    var name: String = "",
    var level: Int = 0,
    var school: String = "",
    var castingTime: String = "",
    var range: String = "",
    var components: String = "",
    var duration: String = "",
    var description: String = "",
    var isPrepared: Boolean = false
)

