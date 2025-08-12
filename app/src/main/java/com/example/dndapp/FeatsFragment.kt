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
import com.google.android.material.search.SearchBar

class FeatsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var searchBar: SearchBar
    private lateinit var featsAdapter: FeatsAdapter
    private var allFeats = listOf<Feat>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.feats_recycler_view)
        emptyView = view.findViewById(R.id.empty_view)
        searchBar = view.findViewById(R.id.searchBar)

        setupRecyclerView()
        setupSearchBar()
        loadFeats()
    }

    private fun setupRecyclerView() {
        featsAdapter = FeatsAdapter { feat ->
            // TODO: Navigate to feat details
        }
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = featsAdapter
        }
    }

    private fun setupSearchBar() {
        searchBar.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterFeats(s?.toString() ?: "")
            }
        })
    }

    private fun filterFeats(query: String) {
        val filteredFeats = if (query.isEmpty()) {
            allFeats
        } else {
            allFeats.filter { feat ->
                feat.name.contains(query, ignoreCase = true) ||
                feat.category.contains(query, ignoreCase = true) ||
                feat.description.contains(query, ignoreCase = true)
            }
        }
        featsAdapter.submitList(filteredFeats)
        updateEmptyView(filteredFeats.isEmpty())
    }

    private fun loadFeats() {
        allFeats = DataManager.feats
        featsAdapter.submitList(allFeats)
        updateEmptyView(allFeats.isEmpty())
    }

    private fun updateEmptyView(isEmpty: Boolean) {
        emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
