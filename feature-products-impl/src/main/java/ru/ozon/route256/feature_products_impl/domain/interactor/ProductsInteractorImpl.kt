package ru.ozon.route256.feature_products_impl.domain.interactor

import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

class ProductsInteractorImpl(private val repository: ProductsRepository): ProductsInteractor {

    override fun getProducts(): List<ProductInList> =
        repository.getProducts()

    override fun increaseProductCounter(guid: String) {
        repository.increaseProductCounter(guid)
    }

}
