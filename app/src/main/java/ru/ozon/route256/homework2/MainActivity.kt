package ru.ozon.route256.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment
import ru.ozon.route256.homework2.di.ApplicationComponent
import ru.ozon.route256.homework2.di.FeatureInjectorProxy
import ru.ozon.route256.homework2.network_handler.ConnectivityEvent
import ru.ozon.route256.homework2.network_handler.ConnectivityHandler
import javax.inject.Inject
import ru.ozon.route256.core_utils.R as core_R

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityHandler: ConnectivityHandler

    private var _snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        handleNetworkUpdates()

        openProductsScreen()
    }

    private fun handleNetworkUpdates() {
        lifecycleScope.launchWhenStarted {
            connectivityHandler.getConnectivityFlow()
                .collect { event ->
                    onConnectionChange(event)
                }
        }
    }

    private fun onConnectionChange(event: ConnectivityEvent) {
        when (event) {
            ConnectivityEvent.LOST -> {
                val message = applicationContext.resources.getString(core_R.string.common_connection_exception_message)
                _snackBar = Snackbar.make(
                    this@MainActivity.findViewById(R.id.fragmentContainer),
                    message,
                    Snackbar.LENGTH_INDEFINITE
                ).also { it.show() }
            }
            ConnectivityEvent.AVAILABLE -> {
                _snackBar?.dismiss()
            }
        }
    }

    private fun openProductsScreen() {
        FeatureInjectorProxy.initFeatureProductsDI()
        val newFragment = ProductsFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, ProductsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    private fun injectDependencies() {
        ApplicationComponent.appComponent?.inject(this)
    }
}
