package ru.ozon.route256.feature_products_impl.presentation.view_holders

import ru.ozon.route256.feature_products_impl.databinding.ProductsSectionItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

class SectionViewHolder(
    private val binding: ProductsSectionItemBinding
) : BaseListViewHolder<ProductsListItem.SectionListItem>(binding) {

    override fun bindItem(item: ProductsListItem.SectionListItem, payloads: MutableList<Any>?) {
        binding.title.setText(item.titleRes)
    }
}