package com.example.recipe

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class ProgressButton(context: Context, view: View) {

    private var cardView: CardView = view.findViewById(R.id.cardView)
    private var constraintLayout = view.findViewById<ConstraintLayout>(R.id.constraint_layout)
    private var progressBar : ProgressBar = view.findViewById(R.id.progressBarButton)
    private var textView : TextView = view.findViewById(R.id.progress_button_textView)

    fun buttonActivated() {
        constraintLayout.setBackgroundColor(cardView.resources.getColor(R.color.colorPrimary))
        progressBar.visibility = View.VISIBLE
        textView.text = "Please Wait..."
    }

    fun buttonFinished() {
        constraintLayout.setBackgroundColor(Color.GREEN)
        progressBar.visibility = View.INVISIBLE
        textView.text = "Done"
    }
}