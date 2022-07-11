package ru.ozon.route256.homework2

import androidx.fragment.app.Fragment
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.homework2.di.FeatureInjectorProxy
import javax.inject.Inject

class ProductsNavigationApiImpl @Inject constructor() : ProductsNavigationApi {

    override fun openPDPScreen(fragment: Fragment, productId: String) {
        FeatureInjectorProxy.initFeaturePDPDI()
        val newFragment = PDPFragment.newInstance(productId)
        fragment.activity
            ?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment.TAG)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureProductsClosed(fragment: Fragment): Boolean {
        return if (fragment !is PDPFragment) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(PDPFragment.TAG) == null
        } else {
            true
        }
    }
}
