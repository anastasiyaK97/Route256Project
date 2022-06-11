package ru.ozon.route256.feature_pdp_impl.domain.interactor

import ru.ozon.route256.feature_pdp_impl.presentation.view_object.Product

interface ProductInteractor {

    fun getProductById(guid: String): Product?
}
