package ru.ozon.route256.core_network_impl.data

import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.core_network_impl.mock.productInListDTOs
import ru.ozon.route256.core_network_impl.mock.productsDTOs
import ru.ozon.route256.core_utils.extensions.replace
import javax.inject.Inject

class ProductsApiImpl @Inject constructor(): ProductsApi {

    override fun getProducts(): List<ProductInListDTO> = productInListDTOs

    override fun getProductById(guid: String): ProductDTO? =
        productsDTOs.find { it.guid == guid }

    override fun increaseProductCounter(guid: String) {
        val product = productInListDTOs.find { it.guid == guid }
        product?.let {
            productInListDTOs.replace(
                oldItem = product,
                newItem = product.copy(visitorCounter = product.visitorCounter + 1)
            )
        }
    }
}
