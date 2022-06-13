package ru.ozon.route256.feature_products_impl.domain.interactor

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val repository: ProductsRepository
): ProductsInteractor {

    override fun getProducts(): List<ProductInListEntity> =
        repository.getProducts()

    override fun increaseProductCounter(guid: String) {
        repository.increaseProductCounter(guid)
    }

}
