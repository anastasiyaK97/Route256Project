package ru.ozon.route256.homework2.network_handler

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityHandlerImpl @Inject constructor(): ConnectivityHandler {

    private val _events = MutableSharedFlow<ConnectivityEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun onConnectivityEvent(event: ConnectivityEvent) {
        _events.tryEmit(event)
    }

    override fun getConnectivityFlow(): Flow<ConnectivityEvent> = _events.asSharedFlow()
}
