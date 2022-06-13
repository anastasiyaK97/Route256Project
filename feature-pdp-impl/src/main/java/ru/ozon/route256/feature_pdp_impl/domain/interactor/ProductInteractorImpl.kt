package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository
import ru.ozon.route256.feature_pdp_impl.domain.model.Product
import javax.inject.Inject

class ProductInteractorImpl @Inject constructor(
    private val repository: ProductRepository
) : ProductInteractor {

    override fun getProductById(guid: String): Product? =
        repository.getProductById(guid)

}
