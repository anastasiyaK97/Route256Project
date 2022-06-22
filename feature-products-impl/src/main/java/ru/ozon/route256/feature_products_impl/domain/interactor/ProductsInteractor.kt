package ru.ozon.route256.feature_products_impl.domain.interactor

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity

interface ProductsInteractor {

    fun getProducts(): List<ProductInListEntity>

    fun planProductsRequest()

    fun increaseProductCounter(guid: String)
}
