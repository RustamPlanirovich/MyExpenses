package com.nauka.myexpenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GeneralSetting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_setting)
        supportActionBar?.hide()
    }
}