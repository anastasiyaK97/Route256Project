package ru.ozon.route256.feature_pdp_impl.domain.repository

import ru.ozon.route256.feature_pdp_impl.domain.model.Product

interface ProductRepository {

    fun getProductById(guid: String): Product?

    fun findInCart(guid: String): Int

    fun addToCart(guid: String, count: Int)
}
