package ru.ozon.route256.feature_products_impl.presentation.mapper

import ru.ozon.route256.core_utils.R
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

fun ProductInListEntity.toProductInList(): ProductsListItem.ProductInList =
    ProductsListItem.ProductInList(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        counter = counter ?: 0
    )

fun List<ProductInListEntity>.toProductsItemList(): List<ProductsListItem> =
    mutableListOf<ProductsListItem>().also { items ->
        val (cheap, expensive) = this.partition { it.price.toInt() <= 100 }
        if (cheap.isNotEmpty()) {
            items.add(ProductsListItem.SectionListItem(R.string.cheap_products_title))
            items.addAll(cheap.map { it.toProductInList() })
        }
        if (expensive.isNotEmpty()) {
            items.add(ProductsListItem.SectionListItem(R.string.expensive_products_title))
            items.addAll(expensive.map { it.toProductInList() })
        }
    }
