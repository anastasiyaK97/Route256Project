package ru.ozon.route256.feature_products_impl.domain.repository

import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

interface ProductsRepository {

    fun getProducts(): List<ProductInList>

    fun increaseProductCounter(guid: String)
}
