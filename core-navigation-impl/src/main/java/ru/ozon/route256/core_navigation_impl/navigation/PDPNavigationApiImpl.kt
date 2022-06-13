package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment
import javax.inject.Inject

class PDPNavigationApiImpl @Inject constructor(): PDPNavigationApi {
    override fun isFeatureProductDetailsClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != ProductsFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductsFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}