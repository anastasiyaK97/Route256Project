package ru.ozon.route256.core_network_impl.data

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.core_network_impl.mock.productInListDTOs
import ru.ozon.route256.core_network_impl.mock.productsDTOs

class ProductsApiImpl: ProductsApi {

    override fun getProducts(): List<ProductDTO> = productsDTOs

    override fun getProductById(guid: String): ProductInListDTO? =
        productInListDTOs.find { it.guid == guid }
}
