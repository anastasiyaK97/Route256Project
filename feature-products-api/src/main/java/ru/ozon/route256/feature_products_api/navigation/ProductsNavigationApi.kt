package ru.ozon.route256.feature_products_api.navigation

import androidx.fragment.app.Fragment

interface ProductsNavigationApi {

    fun openPDPScreen(fragment: Fragment, productId: String)

    fun isFeatureProductsClosed(fragment: Fragment): Boolean
}
