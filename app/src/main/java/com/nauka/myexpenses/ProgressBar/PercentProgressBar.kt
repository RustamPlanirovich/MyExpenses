package com.nauka.myexpenses.ProgressBar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class PercentProgressBar {
    fun Progresses(summPotrch: ProgressBar, progressBar2: ProgressBar, mounthSumm: TextView) {

        summPotrch.max = 2000
        summPotrch.progress = mounthSumm.text.toString().toInt()

        progressBar2.max = 2000
        progressBar2.progress = mounthSumm.text.toString().toInt().rem(2000)
    }
}