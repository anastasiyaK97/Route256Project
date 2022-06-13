package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import javax.inject.Inject

class ProductsNavigationApiImpl @Inject constructor(): ProductsNavigationApi {

    override fun openPDPScreen(fragment: Fragment, productId: String) {
        FeatureInjectorProxy.initFeaturePDPDI()
        val newFragment = PDPFragment.newInstance(productId)
        fragment.activity
            ?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureProductsClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != PDPFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(PDPFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}
