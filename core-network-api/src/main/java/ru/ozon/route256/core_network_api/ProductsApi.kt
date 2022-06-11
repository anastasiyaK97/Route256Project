package ru.ozon.route256.core_network_api

import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO

interface ProductsApi {

    fun getProducts(): List<ProductInListDTO>

    fun getProductById(guid: String): ProductDTO?

    fun increaseProductCounter(guid: String)
}
