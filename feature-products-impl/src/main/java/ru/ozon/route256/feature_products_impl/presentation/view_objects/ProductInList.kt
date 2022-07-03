package ru.ozon.route256.feature_products_impl.presentation.view_objects

import androidx.annotation.StringRes

sealed class ProductsListItem  {

    data class ProductInList(
        val guid: String,
        val image: String,
        val name: String,
        val price: String,
        val rating: Double,
        val isFavorite: Boolean,
        val isInCart: Boolean,
        val counter: Int,
        val isLoading: Boolean = false
    ): ProductsListItem()

    data class SectionListItem(@StringRes val titleRes: Int): ProductsListItem()
}

