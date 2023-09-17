package com.example.techtask.app.utils.extensions

import android.view.View

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.gone(isGone: Boolean = true) {
    this.visibility = if (isGone) View.GONE else View.VISIBLE
}