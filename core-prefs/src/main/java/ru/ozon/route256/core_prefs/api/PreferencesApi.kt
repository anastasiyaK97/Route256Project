package ru.ozon.route256.core_prefs.api

import android.content.SharedPreferences
import ru.ozon.route256.core_prefs.di.ProductsDetailsPrefs
import ru.ozon.route256.core_prefs.di.ProductsPrefs

interface PreferencesApi {

    @ProductsPrefs
    fun getPrefs(): SharedPreferences

    @ProductsDetailsPrefs
    fun getDetailedPrefs(): SharedPreferences
}