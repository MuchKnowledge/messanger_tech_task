package com.example.techtask.app.utils.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboardAndClearFocus() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = window.decorView.rootView
    view.clearFocus()
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.errorToast(message: String = "Error") =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()