package ru.ozon.route256.homework2.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.homework2.network_handler.ConnectivityHandler
import ru.ozon.route256.homework2.network_handler.ConnectivityHandlerImpl

@Module
abstract class NetworkConnectivityModule {

    @Binds
    abstract fun connectivityHandler(impl: ConnectivityHandlerImpl): ConnectivityHandler
}
