package com.nauka.myexpenses.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nauka.myexpenses.R
import kotlinx.android.synthetic.main.activity_main.*

class ChoceTheme : DialogFragment() {
    private var choceTheme: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choceTheme = inflater.inflate(
            R.layout.choice_of_subject,
            container, false

        )
        return choceTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}