package com.darabi.testapplication.util

import android.view.View

fun View.invisible() {
    if (visibility != View.INVISIBLE)
        visibility = View.INVISIBLE
}

fun View.fadeOut() {
    if (visibility != View.GONE)
        visibility = View.GONE
}

fun View.fadeIn() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}