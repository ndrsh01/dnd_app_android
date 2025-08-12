package com.example.dndapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SpellsAdapter(
    private val onSpellClick: (Spell) -> Unit
) : ListAdapter<Spell, SpellsAdapter.SpellViewHolder>(SpellDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spell, parent, false)
        return SpellViewHolder(view, onSpellClick)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SpellViewHolder(
        itemView: View,
        private val onSpellClick: (Spell) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.spell_name)
        private val levelTextView: TextView = itemView.findViewById(R.id.spell_level)
        private val schoolTextView: TextView = itemView.findViewById(R.id.spell_school)
        private val classesTextView: TextView = itemView.findViewById(R.id.spell_classes)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.spell_description)

        fun bind(spell: Spell) {
            nameTextView.text = spell.name
            levelTextView.text = if (spell.level == 0) "Cantrip" else spell.level.toString()
            schoolTextView.text = spell.school
            classesTextView.text = spell.classes.joinToString(", ")
            descriptionTextView.text = spell.description

            itemView.setOnClickListener {
                onSpellClick(spell)
            }
        }
    }

    private class SpellDiffCallback : DiffUtil.ItemCallback<Spell>() {
        override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem == newItem
        }
    }
}
