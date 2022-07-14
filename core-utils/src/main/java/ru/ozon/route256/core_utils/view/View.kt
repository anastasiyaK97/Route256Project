package ru.ozon.route256.core_utils.view

import android.content.res.Resources
import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrGone(text: String?) {
    this.isVisible = text != null
    text?.let {
        this.text = text
    }
}

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
