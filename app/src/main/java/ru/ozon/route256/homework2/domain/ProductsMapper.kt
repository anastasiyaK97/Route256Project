package ru.ozon.route256.homework2.domain

import Product
import ru.ozon.route256.homework2.data.dto.ProductDTO
import ru.ozon.route256.homework2.data.dto.ProductInListDTO
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

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

fun ProductDTO.toProduct() : Product =
    Product(
        guid = guid,
        images = images,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        count = count,
        weight = weight,
        availableCount = availableCount,
        description = description,
        additionalParams = additionalParams
    )
