package ru.ozon.route256.feature_products_impl.presentation.mapper

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

fun ProductInListEntity.toProductInList() : ProductInList =
    ProductInList(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        counter = counter ?: 0
    )
