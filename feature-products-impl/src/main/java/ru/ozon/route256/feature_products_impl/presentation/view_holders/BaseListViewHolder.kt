package ru.ozon.route256.feature_products_impl.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

abstract class BaseListViewHolder<T>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root)
        where T : ProductsListItem {

    @Suppress("UNCHECKED_CAST")
    fun bind(item: ProductsListItem) {
        (item as? T)?.let {
            bindItem(it)
        }

    }

    protected abstract fun bindItem(item: T)
}
