package ru.ozon.route256.my_project.network_handler

import kotlinx.coroutines.flow.Flow

interface ConnectivityHandler {

    fun onConnectivityEvent(event: ConnectivityEvent)

    fun getConnectivityFlow(): Flow<ConnectivityEvent>
}
