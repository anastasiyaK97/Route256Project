package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.domain.model.Product
import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository
import javax.inject.Inject

class ProductInteractorImpl @Inject constructor(
    private val repository: ProductRepository
) : ProductInteractor {

    override fun getProductById(guid: String): Product? =
        repository.getProductById(guid)

    override fun findInCart(guid: String): Int =
        repository.findInCart(guid)

    override fun addToCart(guid: String, count: Int) {
        repository.addToCart(guid, count)
    }
}
