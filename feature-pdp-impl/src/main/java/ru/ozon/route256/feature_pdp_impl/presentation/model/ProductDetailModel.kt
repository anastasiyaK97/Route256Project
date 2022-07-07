package ru.ozon.route256.feature_pdp_impl.presentation.model

import ru.ozon.route256.feature_pdp_impl.domain.model.Product

data class ProductDetailModel(
    val product: Product,
    val isLoading: Boolean = false,
    val countInCart: Int = 0
)
