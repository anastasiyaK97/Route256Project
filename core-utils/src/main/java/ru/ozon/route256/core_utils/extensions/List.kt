package ru.ozon.route256.core_utils.extensions

fun <T> MutableList<T>.replace(oldItem: T, newItem: T) = this
    .apply { this[indexOf(oldItem)] = newItem }
