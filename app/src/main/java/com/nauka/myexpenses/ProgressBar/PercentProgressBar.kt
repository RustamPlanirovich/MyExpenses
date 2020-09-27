package com.nauka.myexpenses.ProgressBar

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class PercentProgressBar {
    fun Progresses(
        summPotrch: ProgressBar,
        progressBar2: ProgressBar,
        sumBy: Int
    ) {

        summPotrch.max = 2000
        summPotrch.progress = sumBy

        progressBar2.max = 2000
        progressBar2.progress = sumBy.rem(2000)
    }
}