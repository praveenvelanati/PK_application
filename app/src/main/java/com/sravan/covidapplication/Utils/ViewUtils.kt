package com.sravan.covidapplication.Utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun showSnackBar(view: View, message: String) {

    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun showSnackLongBar(view: View, message: String) {

    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    println("This is first commit")
    println("Remove this line")
}