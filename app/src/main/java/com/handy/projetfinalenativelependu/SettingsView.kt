package com.handy.projetfinalenativelependu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class SettingsView : AppCompatActivity() {
    lateinit var  radioGroupSettingsLangue: RadioGroup
    lateinit var  radioGroupDifficulty: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_view)

        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty)
        radioGroupSettingsLangue = findViewById(R.id.radioGroupSettingsLangue)



        fun setLocale(activity: Activity, langCode: String) {
            val locale = Locale(langCode)
            Locale.setDefault(locale)
            val resources: Resources = activity.resources
            val config: Configuration = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }

    }
    fun showDictionary(view: View) {
        val intent = Intent(this, DictionaryView::class.java)
        startActivity(intent)
    }

    fun saveSettings(view: View) {
        val preferences:Preferences
        val selectedLangueId = radioGroupSettingsLangue.checkedRadioButtonId
        val selectedDifficulteId = radioGroupDifficulty.checkedRadioButtonId

        val langue = when (selectedLangueId) {
            R.id.radioButtonSettingsFrancais -> "Francais"
            R.id.radioButtonSettingsAnglais -> "Anglais"
            else -> "Francais"
        }

        val difficulte = when (selectedDifficulteId) {
            R.id.radioSettingsFacile -> "Facile"
            R.id.radioSettingsMoyen -> "Moyen"
            R.id.radioSettingsDifficile -> "Difficile"
            else -> "Facile"
        }

        preferences = Preferences(difficulte,langue)
        savePreferences(preferences)


        Toast.makeText(this, "Langue : ${preferences.languePreferences} Et Dificulté : ${preferences.difficultePreferences} Enregistré!", Toast.LENGTH_SHORT).show()
    }

    private fun savePreferences(preferences: Preferences) {
        val sharedPref: SharedPreferences = getSharedPreferences("appPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("languePreferences", preferences.languePreferences)
            putString("difficultePreferences", preferences.difficultePreferences)
            apply()
        }
    }
}
