package com.example.dndapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.search.SearchBar

class SpellsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var filterFab: FloatingActionButton
    private lateinit var searchBar: SearchBar
    private lateinit var spellsAdapter: SpellsAdapter
    private var allSpells = listOf<Spell>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.spells_recycler_view)
        emptyView = view.findViewById(R.id.empty_view)
        filterFab = view.findViewById(R.id.fab_filter)
        searchBar = view.findViewById(R.id.searchBar)

        setupRecyclerView()
        setupFilterButton()
        setupSearchBar()
        loadSpells()
    }

    private fun setupRecyclerView() {
        spellsAdapter = SpellsAdapter { spell ->
            showSpellDetail(spell)
        }
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = spellsAdapter
        }
    }

    private fun setupFilterButton() {
        filterFab.setOnClickListener {
            // TODO: Show filter dialog
        }
    }

    private fun setupSearchBar() {
        searchBar.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterSpells(s?.toString() ?: "")
            }
        })
    }

    private fun filterSpells(query: String) {
        val filteredSpells = if (query.isEmpty()) {
            allSpells
        } else {
            allSpells.filter { spell ->
                spell.name.contains(query, ignoreCase = true) ||
                spell.school.contains(query, ignoreCase = true) ||
                spell.classes.any { it.contains(query, ignoreCase = true) } ||
                spell.description.contains(query, ignoreCase = true)
            }
        }
        spellsAdapter.submitList(filteredSpells)
        updateEmptyView(filteredSpells.isEmpty())
    }

    private fun showSpellDetail(spell: Spell) {
        val fragment = SpellDetailFragment.newInstance(spell)
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun loadSpells() {
        allSpells = DataManager.spells
        spellsAdapter.submitList(allSpells)
        updateEmptyView(allSpells.isEmpty())
    }

    private fun updateEmptyView(isEmpty: Boolean) {
        emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
