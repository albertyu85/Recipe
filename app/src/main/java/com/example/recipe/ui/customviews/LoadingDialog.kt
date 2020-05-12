package com.example.recipe.ui.customviews

import android.app.Activity
import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.recipe.R

class LoadingDialog(private var activity : Activity) {

    private lateinit var dialog: AlertDialog

    fun startLoadingAnimation() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}