package ru.ozon.route256.feature_products_impl.data.mapper

import ru.ozon.route256.core_network_api.model.product.ProductInListDTO
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity

fun ProductInListDTO.toProductInListEntity() : ProductInListEntity =
    ProductInListEntity(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        counter = 0
    )
