package ru.ozon.route256.homework2

import ru.ozon.route256.feature_pdp_api.navigation.FeatureProductNavigationApi
import ru.ozon.route256.feature_products_api.navigation.FeatureProductsNavigationApi

class Navigator: FeatureProductsNavigationApi, FeatureProductNavigationApi {

    override fun openProductDetailScreen(id: String) {
        TODO("Not yet implemented")
    }

    override fun isFeatureProductDetailsClosed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isFeatureProductsClosed(): Boolean {
        TODO("Not yet implemented")
    }

}
