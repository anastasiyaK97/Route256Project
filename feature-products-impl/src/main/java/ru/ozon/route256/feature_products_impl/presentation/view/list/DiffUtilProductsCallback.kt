package ru.ozon.route256.feature_products_impl.presentation.view.list

import androidx.recyclerview.widget.DiffUtil
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

class DiffUtilProductsCallback: DiffUtil.ItemCallback<ProductsListItem>() {
    override fun areItemsTheSame(oldItem: ProductsListItem, newItem: ProductsListItem): Boolean = when {
        oldItem is ProductsListItem.SectionListItem && newItem is ProductsListItem.SectionListItem ->
            oldItem.titleRes == newItem.titleRes
        oldItem is ProductsListItem.ProductInList && newItem is ProductsListItem.ProductInList ->
            oldItem.guid == newItem.guid
        else -> false
    }

    override fun areContentsTheSame(oldItem: ProductsListItem, newItem: ProductsListItem): Boolean = when {
        oldItem is ProductsListItem.SectionListItem && newItem is ProductsListItem.SectionListItem ->
            oldItem == newItem
        oldItem is ProductsListItem.ProductInList && newItem is ProductsListItem.ProductInList ->
            oldItem == newItem
        else -> false
    }

    override fun getChangePayload(oldItem: ProductsListItem, newItem: ProductsListItem): Any? = when {
        oldItem is ProductsListItem.ProductInList && newItem is ProductsListItem.ProductInList -> {
            ProductPayload(isLoading = newItem.isLoading, isInCart = newItem.isInCart)
        }
        else ->super.getChangePayload(oldItem, newItem)
    }
}
