package com.nauka.myexpenses

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_general_setting.*
import kotlinx.android.synthetic.main.choice_of_subject.*

class GeneralSetting : AppCompatActivity() {
    private lateinit var choiceTheme: ChoiceTheme
    private lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_setting)
        supportActionBar?.hide()
        context = this
        choiceTheme = ChoiceTheme(context as GeneralSetting)

        //Открытие диалогового окна смены темы
        ThemeCardView.setOnClickListener {
            val alertDialog = Dialog(this)
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            alertDialog.setContentView(R.layout.choice_of_subject)
            alertDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            val white = alertDialog.white
            val black = alertDialog.black
            val system = alertDialog.system

            white.isChecked = choiceTheme.white()
            black.isChecked = choiceTheme.black()
            system.isChecked = choiceTheme.system()

            white.setOnClickListener {
                choiceTheme.saveStateRadioButton(
                    context as GeneralSetting,
                    white.text.toString()
                )
                alertDialog.dismiss()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            black.setOnClickListener {
                choiceTheme.saveStateRadioButton(
                    context as GeneralSetting,
                    black.text.toString()
                )
                alertDialog.dismiss()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            system.setOnClickListener {
                choiceTheme.saveStateRadioButton(
                    context as GeneralSetting,
                    system.text.toString()
                )
                alertDialog.dismiss()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            alertDialog.show()
        }
    }
}