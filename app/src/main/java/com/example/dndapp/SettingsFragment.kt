package com.example.dndapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSettings()
    }

    private fun setupSettings() {
        view?.findViewById<View>(R.id.setting_dark_mode)?.setOnClickListener {
            showThemeDialog()
        }
        
        view?.findViewById<View>(R.id.setting_about)?.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun showThemeDialog() {
        val themes = arrayOf("Светлая тема", "Тёмная тема", "Системная тема")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Выберите тему")
            .setItems(themes) { _, which ->
                // TODO: Implement theme switching
            }
            .show()
    }

    private fun showAboutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("О приложении")
            .setMessage("DnD App v1.0\n\nПриложение для игроков Dungeons & Dragons 5e\n\nФункции:\n• Справочник заклинаний\n• Справочник умений\n• Цитаты табакси\n• Заметки\n\nСоздано для образовательных целей.")
            .setPositiveButton("OK", null)
            .show()
    }
}
