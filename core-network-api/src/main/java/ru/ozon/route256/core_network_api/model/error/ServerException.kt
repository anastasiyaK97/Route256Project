package ru.ozon.route256.core_network_api.model.error

import java.io.IOException

data class ServerException(
    val exceptionType: ApiErrorType,
    override val message: String? = null
) : IOException(message)