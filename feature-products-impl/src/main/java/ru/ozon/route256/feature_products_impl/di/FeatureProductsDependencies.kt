package ru.ozon.route256.feature_products_impl.di

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi

interface FeatureProductsDependencies {

    fun productsApi(): ProductsApi
    fun productsNavigationApi(): ProductsNavigationApi
}
