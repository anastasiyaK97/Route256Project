package ru.ozon.route256.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment
import ru.ozon.route256.homework2.di.FeatureInjectorProxy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openProductsScreen()
    }

    private fun openProductsScreen() {
        FeatureInjectorProxy.initFeatureProductsDI()
        val newFragment = ProductsFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, ProductsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }
}
