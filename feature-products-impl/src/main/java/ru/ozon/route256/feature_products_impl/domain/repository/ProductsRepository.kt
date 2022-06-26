package ru.ozon.route256.feature_products_impl.domain.repository

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity

interface ProductsRepository {

    fun getProductsFromCache(): List<ProductInListEntity>

    fun saveProductsInCache(productsJson: String)

    fun updateProductsInCache(productslist: List<ProductInListEntity>)

    fun requestProducts()
}
