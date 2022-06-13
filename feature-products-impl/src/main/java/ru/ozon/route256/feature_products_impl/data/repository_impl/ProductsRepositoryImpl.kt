package ru.ozon.route256.feature_products_impl.data.repository_impl

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_products_impl.data.mapper.toProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsRepository {

    override fun getProducts(): List<ProductInListEntity> =
        productsApi.getProducts().map { it.toProductInListEntity() }

    override fun increaseProductCounter(guid: String) =
        productsApi.increaseProductCounter(guid)

}
