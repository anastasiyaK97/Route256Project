package ru.ozon.route256.feature_products_impl.data.mapper

import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

fun ProductInListDTO.toProductInList() : ProductInList =
    ProductInList(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        counter = visitorCounter
    )
