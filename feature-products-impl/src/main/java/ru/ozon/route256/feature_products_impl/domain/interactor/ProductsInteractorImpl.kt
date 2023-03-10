package ru.ozon.route256.feature_products_impl.domain.interactor

import ru.ozon.route256.core_utils.extensions.replace
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val repository: ProductsRepository
) : ProductsInteractor {

    override fun planProductsRequest() {
        repository.requestProducts()
    }

    override fun getProducts(): List<ProductInListEntity> =
        repository.getProductsFromCache()

    override fun increaseProductCounter(guid: String) {
        val productToReplace = getProducts().find { it.guid == guid } ?: return
        val newCount = (productToReplace.counter ?: 0) + 1
        val products = getProducts().toMutableList().replace(
            oldItem = productToReplace,
            newItem = productToReplace.copy(counter = newCount)
        )
        repository.updateProductsInCache(products)
    }

    override fun addToCart(guid: String, count: Int) {
        repository.addToCart(guid, count)
    }
}
