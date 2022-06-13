package ru.ozon.route256.homework2.domain.interactors

import Product
import ru.ozon.route256.homework2.domain.repositories.ProductsRepository
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

class ProductsInteractorImpl(private val repository: ProductsRepository): ProductsInteractor {
    override fun getProducts(): List<ProductInList> =
        repository.getProducts()

    override fun getProductById(guid: String): Product? =
        repository.getProductById(guid)

    override fun increaseProductCounter(guid: String) {
        repository.increaseProductCounter(guid)
    }

}
