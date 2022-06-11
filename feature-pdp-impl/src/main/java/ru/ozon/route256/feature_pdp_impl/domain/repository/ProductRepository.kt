package ru.ozon.route256.feature_pdp_impl.domain.repository

import ru.ozon.route256.feature_pdp_impl.presentation.view_object.Product

interface ProductRepository {

    fun getProductById(guid: String): Product?
}
