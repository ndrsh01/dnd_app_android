package com.example.dndapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuotesFragment : Fragment() {

    private lateinit var quoteTextView: TextView
    private lateinit var categoryTextView: TextView
    private lateinit var randomButton: Button

    private val quotes = DataManager.quotes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteTextView = view.findViewById(R.id.quote_text)
        categoryTextView = view.findViewById(R.id.quote_category)
        randomButton = view.findViewById(R.id.random_quote_button)

        randomButton.setOnClickListener {
            showRandomQuote()
        }

        showRandomQuote()
    }

    private fun showRandomQuote() {
        val randomQuote = quotes.random()
        quoteTextView.text = randomQuote.text
        categoryTextView.text = randomQuote.category
    }
}
