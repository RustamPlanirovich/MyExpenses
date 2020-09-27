package com.nauka.myexpenses

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_panel_setting.*


class PanelSetting : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_setting)
        supportActionBar?.hide()

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
    }


    //Записываем все данные при закрытии активити
    override fun onPause() {
        super.onPause()

        // Запоминаем данные
        val editor = prefs.edit()
        editor.putBoolean("switchcredit", switchcredit.isChecked).apply()
        editor.putBoolean("switchrashod", switchrashod.isChecked).apply()
        editor.putBoolean("switchbudgit", switchbudgit.isChecked).apply()
        editor.putBoolean("switchostatok", switchostatok.isChecked).apply()
        editor.putBoolean("switchschet", switchschet.isChecked).apply()
        editor.putBoolean("switchceli", switchceli.isChecked).apply()
        editor.putBoolean("switchekonom", switchekonom.isChecked).apply()
    }

    //Загружаем все данные при открытии активити
    override fun onResume() {
        super.onResume()

        if (prefs.contains("switchcredit")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchcredit", true).toString()
            switchcredit.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchrashod")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchrashod", true).toString()
            switchrashod.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchbudgit")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchbudgit", true).toString()
            switchbudgit.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchostatok")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchostatok", true).toString()
            switchostatok.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchschet")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchschet", true).toString()
            switchschet.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchceli")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchceli", true).toString()
            switchceli.isChecked = state.toBoolean()
        }
        if (prefs.contains("switchekonom")) {
            // Получаем число из настроек
            val state = prefs.getBoolean("switchekonom", true).toString()
            switchekonom.isChecked = state.toBoolean()
        }
    }
}

