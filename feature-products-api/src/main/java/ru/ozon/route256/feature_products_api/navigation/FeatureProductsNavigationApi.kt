package ru.ozon.route256.feature_products_api.navigation

interface FeatureProductsNavigationApi {

    fun openProductDetailScreen(productId: String)

    fun isFeatureProductsClosed(): Boolean
}
