package com.example.dndapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onNoteDelete: (Note) -> Unit
) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, onNoteClick, onNoteDelete)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(
        itemView: View,
        private val onNoteClick: (Note) -> Unit,
        private val onNoteDelete: (Note) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView: TextView = itemView.findViewById(R.id.note_title)
        private val contentTextView: TextView = itemView.findViewById(R.id.note_content)
        private val timestampTextView: TextView = itemView.findViewById(R.id.note_timestamp)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.note_delete_button)

        private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        fun bind(note: Note) {
            titleTextView.text = note.title
            contentTextView.text = note.content
            timestampTextView.text = dateFormat.format(Date(note.timestamp))

            itemView.setOnClickListener {
                onNoteClick(note)
            }

            deleteButton.setOnClickListener {
                onNoteDelete(note)
            }
        }
    }

    private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
