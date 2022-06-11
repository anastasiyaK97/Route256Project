package ru.ozon.route256.core_utils.view

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrGone(text: String?) {
    this.isVisible = text != null
    text?.let {
        this.text = text
    }
}
