package com.example.dndapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FeatsAdapter(
    private val onFeatClick: (Feat) -> Unit
) : ListAdapter<Feat, FeatsAdapter.FeatViewHolder>(FeatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feat, parent, false)
        return FeatViewHolder(view, onFeatClick)
    }

    override fun onBindViewHolder(holder: FeatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeatViewHolder(
        itemView: View,
        private val onFeatClick: (Feat) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.feat_name)
        private val categoryTextView: TextView = itemView.findViewById(R.id.feat_category)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.feat_description)

        fun bind(feat: Feat) {
            nameTextView.text = feat.name
            categoryTextView.text = feat.category
            descriptionTextView.text = feat.description

            itemView.setOnClickListener {
                onFeatClick(feat)
            }
        }
    }

    private class FeatDiffCallback : DiffUtil.ItemCallback<Feat>() {
        override fun areItemsTheSame(oldItem: Feat, newItem: Feat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Feat, newItem: Feat): Boolean {
            return oldItem == newItem
        }
    }
}
