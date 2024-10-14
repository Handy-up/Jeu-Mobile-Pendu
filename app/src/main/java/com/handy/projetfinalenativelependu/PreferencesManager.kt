package com.handy.projetfinalenativelependu

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {

    fun savePreferences(context: Context, preferences: Preferences) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("appPreferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("languePreferences", preferences.languePreferences)
            putString("difficultePreferences", preferences.difficultePreferences)
            apply()
        }
    }

    fun loadPreferences(context: Context): Preferences {
        val sharedPref: SharedPreferences = context.getSharedPreferences("appPreferences", Context.MODE_PRIVATE)
        val langue = sharedPref.getString("languePreferences", "Francais") ?: "Francais"
        val difficulte = sharedPref.getString("difficultePreferences", "Facile") ?: "Facile"
        return Preferences(difficulte, langue)
    }
}