package ru.ozon.route256.core_navigation_api

import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi

interface NavigationApi {

    fun getProductsNavigationApi(): ProductsNavigationApi

    fun getPDPNavigationApi(): PDPNavigationApi
}
