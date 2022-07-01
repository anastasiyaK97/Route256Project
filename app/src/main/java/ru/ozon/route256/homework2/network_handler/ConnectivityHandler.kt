package ru.ozon.route256.homework2.network_handler

import kotlinx.coroutines.flow.Flow

interface ConnectivityHandler {

    fun onConnectivityEvent(event: ConnectivityEvent)

    fun getConnectivityFlow(): Flow<ConnectivityEvent>
}
