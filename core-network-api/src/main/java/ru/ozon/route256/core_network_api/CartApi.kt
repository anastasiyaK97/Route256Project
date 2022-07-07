package ru.ozon.route256.core_network_api

interface CartApi {
    fun addToCart(guid: String, count: Int)

    fun findInCart(guid: String): Int
}
