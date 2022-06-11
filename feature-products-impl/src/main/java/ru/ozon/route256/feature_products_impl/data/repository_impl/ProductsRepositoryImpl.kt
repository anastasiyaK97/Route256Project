package ru.ozon.route256.feature_products_impl.data.repository_impl

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_products_impl.data.mapper.toProductInList
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

class ProductsRepositoryImpl(private val productsApi: ProductsApi): ProductsRepository {

    override fun getProducts(): List<ProductInList> =
        productsApi.getProducts().map { it.toProductInList() }

    override fun increaseProductCounter(guid: String) =
        productsApi.increaseProductCounter(guid)

}
