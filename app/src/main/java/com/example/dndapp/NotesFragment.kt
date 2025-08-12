package com.example.dndapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var addFab: FloatingActionButton
    private lateinit var notesAdapter: NotesAdapter
    private val notes = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.notes_recycler_view)
        emptyView = view.findViewById(R.id.empty_view)
        addFab = view.findViewById(R.id.fab_add_note)

        setupRecyclerView()
        setupAddButton()
        loadNotes()
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(
            onNoteClick = { note ->
                showEditNoteDialog(note)
            },
            onNoteDelete = { note ->
                deleteNote(note)
            }
        )
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }

    private fun setupAddButton() {
        addFab.setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.title_edit_text)
        val contentEditText = dialogView.findViewById<EditText>(R.id.content_edit_text)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Добавить заметку")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = titleEditText.text.toString()
                val content = contentEditText.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    addNote(Note(title = title, content = content))
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun showEditNoteDialog(note: Note) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.title_edit_text)
        val contentEditText = dialogView.findViewById<EditText>(R.id.content_edit_text)

        titleEditText.setText(note.title)
        contentEditText.setText(note.content)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Редактировать заметку")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = titleEditText.text.toString()
                val content = contentEditText.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    updateNote(note.copy(title = title, content = content))
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun addNote(note: Note) {
        notes.add(note)
        notesAdapter.submitList(notes.toList())
        updateEmptyView(notes.isEmpty())
    }

    private fun updateNote(updatedNote: Note) {
        val index = notes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            notes[index] = updatedNote
            notesAdapter.submitList(notes.toList())
        }
    }

    private fun deleteNote(note: Note) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удалить заметку")
            .setMessage("Вы уверены, что хотите удалить заметку '${note.title}'?")
            .setPositiveButton("Удалить") { _, _ ->
                notes.removeAll { it.id == note.id }
                notesAdapter.submitList(notes.toList())
                updateEmptyView(notes.isEmpty())
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun loadNotes() {
        // Загружаем начальные заметки
        if (notes.isEmpty()) {
            val sampleNotes = listOf(
                Note(
                    title = "Идеи для кампании",
                    content = "Создать мир с плавающими островами и воздушными кораблями. Основная угроза - древние драконы, которые просыпаются от тысячелетнего сна."
                ),
                Note(
                    title = "Персонаж - Рогар",
                    content = "Полуорк-варвар, уровень 5. Сила 18, Ловкость 14, Телосложение 16. Любит пиво и драки. Боится высоты."
                ),
                Note(
                    title = "Важные NPC",
                    content = "Мэр города - старый гном-торговец, знает все сплетни. Капитан стражи - бывший наемник, подозрительный тип."
                )
            )
            notes.addAll(sampleNotes)
        }
        
        notesAdapter.submitList(notes.toList())
        updateEmptyView(notes.isEmpty())
    }

    private fun updateEmptyView(isEmpty: Boolean) {
        emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
