package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository
import ru.ozon.route256.feature_pdp_impl.presentation.view_object.Product

class ProductInteractorImpl(private val repository: ProductRepository): ProductInteractor {

    override fun getProductById(guid: String): Product? =
        repository.getProductById(guid)

}
