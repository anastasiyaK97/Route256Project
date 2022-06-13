package ru.ozon.route256.feature_pdp_impl.di

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi

interface FeatureProductDependencies {

    fun productDetailsApi(): ProductsApi
    fun productNavigationApi(): PDPNavigationApi
}
