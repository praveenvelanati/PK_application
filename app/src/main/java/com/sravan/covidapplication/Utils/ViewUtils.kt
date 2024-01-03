package com.sravan.covidapplication.Utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    println("This is third commit")

}

fun showSnackBar(view: View, message: String) {

    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    println("This is first commit")

}

fun showSnackLongBar(view: View, message: String) {

    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    println("This is second commit")
}