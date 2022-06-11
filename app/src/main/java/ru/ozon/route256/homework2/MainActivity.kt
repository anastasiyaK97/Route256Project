package ru.ozon.route256.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openProductsScreen()
    }

    private fun openProductsScreen() {
        val newFragment = ProductsFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(newFragment, ProductsFragment::class.java.simpleName)
            .commit()
    }
}
