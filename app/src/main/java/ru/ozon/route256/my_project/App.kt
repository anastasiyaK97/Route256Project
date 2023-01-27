package ru.ozon.route256.my_project

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import ru.ozon.route256.my_project.di.ApplicationComponent
import ru.ozon.route256.my_project.network_handler.ConnectivityEvent
import ru.ozon.route256.my_project.network_handler.ConnectivityHandler
import javax.inject.Inject

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var connectivityHandler: ConnectivityHandler

    override fun onCreate() {
        super.onCreate()
        initDagger()

        registerNetworkCallback()
    }

    private fun initDagger() {
        appComponent = ApplicationComponent.initAndGet(this)
            ?: throw Exception("appComponent not initialized")
        appComponent.inject(this)
    }

    private fun registerNetworkCallback() {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            connectivityHandler.onConnectivityEvent(ConnectivityEvent.AVAILABLE)
        }

        override fun onLost(network: Network) {
            connectivityHandler.onConnectivityEvent(ConnectivityEvent.LOST)
        }
    }
}
