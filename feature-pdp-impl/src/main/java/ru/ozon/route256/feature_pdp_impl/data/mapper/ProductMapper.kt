package ru.ozon.route256.feature_pdp_impl.data.mapper

import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.feature_pdp_impl.domain.model.Product

fun ProductDTO.toProduct(): Product =
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
