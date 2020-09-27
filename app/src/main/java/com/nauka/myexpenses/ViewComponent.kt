package com.nauka.myexpenses

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class ViewComponent(context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun include2(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchcredit", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include3(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchrashod", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include4(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchbudgit", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include5(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchostatok", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include6(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchschet", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include7(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchceli", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

    fun include8(): Int {
        // Получаем число из настроек
        val state1 = prefs.getBoolean("switchekonom", true).toString()
        return when (state1) {
            "false" -> return View.GONE
            "true" -> return View.VISIBLE
            else -> 0
        }
    }

}