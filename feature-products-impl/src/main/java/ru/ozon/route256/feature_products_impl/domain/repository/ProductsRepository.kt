package ru.ozon.route256.feature_products_impl.domain.repository

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity

interface ProductsRepository {

    fun getProducts(): List<ProductInListEntity>

    fun increaseProductCounter(guid: String)
}
