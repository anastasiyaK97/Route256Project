package ru.ozon.route256.homework2.presentation.extensions

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrGone(text: String?) {
    this.isVisible = text != null
    text?.let {
        this.text = text
    }
}
