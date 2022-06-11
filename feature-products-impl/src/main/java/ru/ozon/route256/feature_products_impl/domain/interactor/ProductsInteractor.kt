package ru.ozon.route256.feature_products_impl.domain.interactor

import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

interface ProductsInteractor {

    fun getProducts(): List<ProductInList>

    fun increaseProductCounter(guid: String)
}
