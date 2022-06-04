package ru.ozon.route256.homework2.domain.repositories

import Product
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

interface ProductsRepository {

    fun getProducts(): List<ProductInList>

    fun getProductById(guid: String): Product?

    fun increaseProductCounter(guid: String)
}
