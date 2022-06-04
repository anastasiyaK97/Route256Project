package ru.ozon.route256.homework2.domain.interactors

import Product
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

interface ProductsInteractor {

    fun getProducts(): List<ProductInList>

    fun getProductById(guid: String): Product?

    fun increaseProductCounter(guid: String)
}
