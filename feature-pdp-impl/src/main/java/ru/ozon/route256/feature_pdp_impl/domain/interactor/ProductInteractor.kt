package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.domain.model.Product

interface ProductInteractor {

    fun getProductById(guid: String): Product?
}
