package ru.ozon.route256.core_network_impl.mock

import ru.ozon.route256.core_network_api.CartApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cart @Inject constructor() : CartApi {

    override fun addToCart(guid: String, count: Int) {
        cart.put(guid, count)
    }

    override fun findInCart(guid: String): Int =
        cart.getOrElse(guid) { 0 }
}
