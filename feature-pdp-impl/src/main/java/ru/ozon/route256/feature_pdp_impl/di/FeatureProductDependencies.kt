package ru.ozon.route256.feature_pdp_impl.di

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.CartApi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_prefs.di.ProductsDetailsPrefs
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi

interface FeatureProductDependencies {

    fun productDetailsApi(): ProductsApi
    fun moshi(): Moshi
    fun getCart(): CartApi

    fun productNavigationApi(): PDPNavigationApi

    @ProductsDetailsPrefs
    fun productsPrefs(): SharedPreferences
}
