package ru.ozon.route256.core_utils.extensions

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

inline fun <reified Type> Moshi.getListAdapter(): JsonAdapter<List<Type>> {
    val type = Types.newParameterizedType(MutableList::class.java, Type::class.java)
    return this.adapter(type)
}
