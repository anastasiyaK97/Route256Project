package ru.ozon.route256.my_project.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.my_project.network_handler.ConnectivityHandler
import ru.ozon.route256.my_project.network_handler.ConnectivityHandlerImpl

@Module
abstract class NetworkConnectivityModule {

    @Binds
    abstract fun connectivityHandler(impl: ConnectivityHandlerImpl): ConnectivityHandler
}
