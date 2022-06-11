package ru.ozon.route256.feature_products_impl.domain.model

data class ProductInListEntity(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val counter: Int
)
