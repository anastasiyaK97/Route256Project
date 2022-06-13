package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.feature_pdp_impl.data.mapper.toProduct
import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository
import ru.ozon.route256.feature_pdp_impl.domain.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductsApi
) : ProductRepository {

    override fun getProductById(guid: String): Product? =
        productApi.getProductById(guid)?.toProduct()
}