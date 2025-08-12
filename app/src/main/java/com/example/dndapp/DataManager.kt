package com.example.dndapp

object DataManager {
    
    val spells = listOf(
        Spell(
            name = "Fireball",
            level = 3,
            school = "Evocation",
            classes = listOf("Wizard", "Sorcerer"),
            actionType = "1 action",
            concentration = false,
            ritual = false,
            castingTime = "1 action",
            range = "150 feet",
            components = listOf("V", "S", "M"),
            duration = "Instantaneous",
            description = "A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.",
            material = "A tiny ball of bat guano and sulfur"
        ),
        Spell(
            name = "Magic Missile",
            level = 1,
            school = "Evocation",
            classes = listOf("Wizard", "Sorcerer"),
            actionType = "1 action",
            concentration = false,
            ritual = false,
            castingTime = "1 action",
            range = "120 feet",
            components = listOf("V", "S"),
            duration = "Instantaneous",
            description = "You create three glowing darts of magical force. Each dart hits a creature of your choice that you can see within range. A dart deals 1d4 + 1 force damage to its target. The darts all strike simultaneously, and you can direct them to hit one creature or several."
        ),
        Spell(
            name = "Cure Wounds",
            level = 1,
            school = "Evocation",
            classes = listOf("Cleric", "Paladin", "Ranger"),
            actionType = "1 action",
            concentration = false,
            ritual = false,
            castingTime = "1 action",
            range = "Touch",
            components = listOf("V", "S"),
            duration = "Instantaneous",
            description = "A creature you touch regains a number of hit points equal to 1d8 + your spellcasting ability modifier. This spell has no effect on undead or constructs."
        ),
        Spell(
            name = "Lightning Bolt",
            level = 3,
            school = "Evocation",
            classes = listOf("Wizard", "Sorcerer"),
            actionType = "1 action",
            concentration = false,
            ritual = false,
            castingTime = "1 action",
            range = "Self (100-foot line)",
            components = listOf("V", "S", "M"),
            duration = "Instantaneous",
            description = "A stroke of lightning forming a line of 100 feet long and 5 feet wide blasts out from you in a direction you choose. Each creature in the line must make a Dexterity saving throw. A creature takes 8d6 lightning damage on a failed save, or half as much damage on a successful one.",
            material = "A bit of fur and a rod of amber, crystal, or glass"
        ),
        Spell(
            name = "Invisibility",
            level = 2,
            school = "Illusion",
            classes = listOf("Wizard", "Sorcerer", "Warlock"),
            actionType = "1 action",
            concentration = true,
            ritual = false,
            castingTime = "1 action",
            range = "Touch",
            components = listOf("V", "S", "M"),
            duration = "Concentration, up to 1 hour",
            description = "A creature you touch and everything it is wearing and carrying becomes invisible until the spell ends. The spell ends for a target that attacks or casts a spell.",
            material = "An eyelash encased in gum arabic"
        ),
        Spell(
            name = "Mage Armor",
            level = 1,
            school = "Abjuration",
            classes = listOf("Wizard"),
            actionType = "1 action",
            concentration = false,
            ritual = false,
            castingTime = "1 action",
            range = "Touch",
            components = listOf("V", "S", "M"),
            duration = "8 hours",
            description = "You touch a willing creature who isn't wearing armor, and a protective magical force surrounds it until the spell ends. The target's base AC becomes 13 + its Dexterity modifier. The spell ends if the target dons armor or if you dismiss the spell as an action.",
            material = "A piece of cured leather"
        ),
        Spell(
            name = "Shield",
            level = 1,
            school = "Abjuration",
            classes = listOf("Wizard", "Sorcerer"),
            actionType = "1 reaction",
            concentration = false,
            ritual = false,
            castingTime = "1 reaction",
            range = "Self",
            components = listOf("V", "S"),
            duration = "1 round",
            description = "An invisible barrier of magical force appears and protects you. Until the start of your next turn, you have a +5 bonus to AC, including against the triggering attack, and you take no damage from magic missile."
        ),
        Spell(
            name = "Counterspell",
            level = 3,
            school = "Abjuration",
            classes = listOf("Wizard", "Sorcerer", "Warlock"),
            actionType = "1 reaction",
            concentration = false,
            ritual = false,
            castingTime = "1 reaction",
            range = "60 feet",
            components = listOf("S"),
            duration = "Instantaneous",
            description = "You attempt to interrupt a creature in the process of casting a spell. If the creature is casting a spell of 3rd level or lower, its spell fails and has no effect. If it is casting a spell of 4th level or higher, make an ability check using your spellcasting ability."
        )
    )

    val feats = listOf(
        Feat(
            name = "Alert",
            description = "Always on the lookout for danger, you gain the following benefits:\n• You gain a +5 bonus to initiative.\n• You can't be surprised while you are conscious.\n• Other creatures don't gain advantage on attack rolls against you as a result of being unseen by you.",
            category = "Combat"
        ),
        Feat(
            name = "Athlete",
            description = "You have undergone extensive physical training to gain the following benefits:\n• Increase your Strength or Dexterity score by 1, to a maximum of 20.\n• When you are prone, standing up uses only 5 feet of your movement.\n• Climbing doesn't cost you extra movement.\n• You can make a running long jump or running high jump after moving only 5 feet on foot, rather than 10 feet.",
            category = "General"
        ),
        Feat(
            name = "Actor",
            description = "Skilled at mimicry and dramatics, you gain the following benefits:\n• Increase your Charisma score by 1, to a maximum of 20.\n• You have an advantage on Charisma (Deception) and Charisma (Performance) checks when trying to pass yourself off as a different person.\n• You can mimic the speech of another person or the sounds made by other creatures. You must have heard the person speaking, or heard the creature make the sound, for at least 1 minute. A successful Wisdom (Insight) check contested by your Charisma (Deception) check allows a listener to determine that the effect is faked.",
            category = "General"
        ),
        Feat(
            name = "Charger",
            description = "When you use your action to Dash, you can use a bonus action to make one melee weapon attack or to shove a creature. If you move at least 10 feet in a straight line immediately before taking this bonus action, you either gain a +5 bonus to the attack's damage roll (if you chose to make a melee attack and hit) or push the target up to 10 feet away from you (if you chose to shove and you succeed).",
            category = "Combat"
        ),
        Feat(
            name = "Defensive Duelist",
            description = "When you are wielding a finesse weapon with which you are proficient and another creature hits you with a melee attack, you can use your reaction to add your proficiency bonus to your AC for that attack, potentially causing the attack to miss you.",
            category = "Combat"
        ),
        Feat(
            name = "Dual Wielder",
            description = "You master fighting with two weapons, gaining the following benefits:\n• You gain a +1 bonus to AC while you are wielding a separate melee weapon in each hand.\n• You can use two-weapon fighting even when the one-handed melee weapons you are wielding aren't light.\n• You can draw or stow two one-handed weapons when you would normally be able to draw or stow only one.",
            category = "Combat"
        ),
        Feat(
            name = "Dungeon Delver",
            description = "Alert to the hidden traps and secret doors found in many dungeons, you gain the following benefits:\n• You have advantage on Wisdom (Perception) and Intelligence (Investigation) checks made to detect the presence of secret doors.\n• You have advantage on saving throws made to avoid or resist traps.\n• You have resistance to the damage dealt by traps.\n• You can search for traps while traveling at a normal pace, instead of only at a slow pace.",
            category = "General"
        ),
        Feat(
            name = "Durable",
            description = "Hardy and resilient, you gain the following benefits:\n• Increase your Constitution score by 1, to a maximum of 20.\n• When you roll a Hit Die to regain hit points, the minimum number of hit points you regain from the roll equals twice your Constitution modifier (minimum of 2).",
            category = "General"
        )
    )

    val quotes = listOf(
        Quote(
            text = "Кто сказал, что кошки не любят воду? Я просто предпочитаю вино!",
            category = "Юмор"
        ),
        Quote(
            text = "Девять жизней? Ха! У меня их как минимум двадцать, и я не собираюсь тратить их попусту.",
            category = "Философия"
        ),
        Quote(
            text = "Высота - это не проблема, проблема - это приземление. Или его отсутствие.",
            category = "Мудрость"
        ),
        Quote(
            text = "Если вы не можете найти выход из ситуации, значит, вы просто недостаточно высоко забрались.",
            category = "Советы"
        ),
        Quote(
            text = "Кошачья грация - это не врожденное качество, это результат многолетних тренировок по избеганию работы.",
            category = "Юмор"
        ),
        Quote(
            text = "Лучший способ спрятаться - это быть на виду. Никто не ожидает, что вы будете так наглы.",
            category = "Стратегия"
        ),
        Quote(
            text = "Мягкие лапки, острые когти. Таков путь табакси.",
            category = "Философия"
        ),
        Quote(
            text = "Если жизнь дает вам лимоны, сделайте из них лимонад. Если жизнь дает вам рыбу, съешьте ее и попросите еще.",
            category = "Мудрость"
        ),
        Quote(
            text = "Я не ленивый, я энергосберегающий. Это экологично!",
            category = "Юмор"
        ),
        Quote(
            text = "Лучший способ решить проблему - это перевернуть ее с ног на голову. Или просто перевернуться самому.",
            category = "Советы"
        ),
        Quote(
            text = "Я не избегаю конфликтов, я просто предпочитаю дипломатические решения. С когтями.",
            category = "Стратегия"
        ),
        Quote(
            text = "Время - это иллюзия. Обед - это реальность.",
            category = "Философия"
        )
    )
}
