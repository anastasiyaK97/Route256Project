package ru.ozon.route256.feature_pdp_api.navigation

import androidx.fragment.app.Fragment

interface PDPNavigationApi {

    fun isFeatureProductDetailsClosed(fragment: Fragment): Boolean
}
