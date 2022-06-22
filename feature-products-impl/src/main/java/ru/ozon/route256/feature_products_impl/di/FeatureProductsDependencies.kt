package ru.ozon.route256.feature_products_impl.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_prefs.di.ProductsDetailsPrefs
import ru.ozon.route256.core_prefs.di.ProductsPrefs
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi

interface FeatureProductsDependencies {

    fun productsApi(): ProductsApi
    fun moshi(): Moshi

    fun productsNavigationApi(): ProductsNavigationApi

    @ProductsPrefs
    fun productsPrefs(): SharedPreferences

    @ProductsDetailsPrefs
    fun productsDetailsPrefs(): SharedPreferences
}
