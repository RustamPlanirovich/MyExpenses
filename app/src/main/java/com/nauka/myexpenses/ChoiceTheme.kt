package com.nauka.myexpenses

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES


class ChoiceTheme(context: Context?) : View(context) {
    private lateinit var prefs: SharedPreferences

    fun saveStateRadioButton(context: Context, buttonName: String) {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("Theme", buttonName).apply()
    }

    fun loadStateRadioButton() {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val themeApp = prefs.getString("Theme", "")
        when (themeApp) {
            "Светлая тема" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            "Темная тема" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            "Системные настройки" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            else -> 0
        }
    }
    fun white(): Boolean {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val themeApp = prefs.getString("Theme", "")
        when (themeApp) {
            "Светлая тема" -> return true
            else -> 0
        }
        return false
    }
    fun black(): Boolean {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val themeApp = prefs.getString("Theme", "")
        when (themeApp) {
            "Темная тема" -> return true
            else -> 0
        }
        return false
    }
    fun system(): Boolean {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val themeApp = prefs.getString("Theme", "")
        when (themeApp) {
            "Системные настройки" -> return true
            else -> 0
        }
        return false
    }
}