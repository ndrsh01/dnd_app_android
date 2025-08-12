package com.example.dndapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SpellDetailFragment : Fragment() {
    
    companion object {
        private const val ARG_SPELL = "spell"
        
        fun newInstance(spell: Spell): SpellDetailFragment {
            val fragment = SpellDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_SPELL, spell)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spell_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val spell = arguments?.getParcelable<Spell>(ARG_SPELL)
        spell?.let { displaySpell(it) }
    }

    private fun displaySpell(spell: Spell) {
        view?.let { view ->
            view.findViewById<TextView>(R.id.spell_name_detail).text = spell.name
            view.findViewById<TextView>(R.id.spell_level_detail).text = 
                if (spell.level == 0) "Cantrip" else spell.level.toString()
            view.findViewById<TextView>(R.id.spell_school_detail).text = spell.school
            view.findViewById<TextView>(R.id.spell_casting_time).text = spell.castingTime ?: "N/A"
            view.findViewById<TextView>(R.id.spell_range).text = spell.range ?: "N/A"
            view.findViewById<TextView>(R.id.spell_components).text = 
                spell.components?.joinToString(", ") ?: "N/A"
            view.findViewById<TextView>(R.id.spell_duration).text = spell.duration ?: "N/A"
            view.findViewById<TextView>(R.id.spell_classes_detail).text = 
                spell.classes.joinToString(", ")
            view.findViewById<TextView>(R.id.spell_concentration).text = 
                if (spell.concentration) "Yes" else "No"
            view.findViewById<TextView>(R.id.spell_ritual).text = 
                if (spell.ritual) "Yes" else "No"
            view.findViewById<TextView>(R.id.spell_description_detail).text = spell.description
            
            // Показываем материал только если он есть
            val materialTextView = view.findViewById<TextView>(R.id.spell_material)
            spell.material?.let { material ->
                materialTextView.text = "Материал: $material"
                materialTextView.visibility = View.VISIBLE
            } ?: run {
                materialTextView.visibility = View.GONE
            }
        }
    }
}
