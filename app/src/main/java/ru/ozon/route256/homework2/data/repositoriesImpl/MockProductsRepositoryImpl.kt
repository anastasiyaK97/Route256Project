package ru.ozon.route256.homework2.data.repositoriesImpl

import Product
import ru.ozon.route256.homework2.data.dto.ProductInListDTO
import ru.ozon.route256.homework2.data.network.productInListDTOs
import ru.ozon.route256.homework2.data.network.productsDTOs
import ru.ozon.route256.homework2.domain.repositories.ProductsRepository
import ru.ozon.route256.homework2.domain.toProduct
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList
import ru.ozon.route256.homework2.domain.toProductInList

class MockProductsRepositoryImpl: ProductsRepository {

    override fun getProducts(): List<ProductInList> = productInListDTOs.map (ProductInListDTO::toProductInList)

    override fun getProductById(guid: String): Product? =
        productsDTOs.find { it.guid == guid }?.toProduct()

    override fun increaseProductCounter(guid: String) {
        productInListDTOs.replaceAll { product ->
            if (product.guid == guid) product.copy(visitorCounter = product.visitorCounter + 1) else product
        }
    }
}
