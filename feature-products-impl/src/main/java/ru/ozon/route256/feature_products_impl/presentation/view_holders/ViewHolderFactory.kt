package ru.ozon.route256.feature_products_impl.presentation.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.databinding.ProductsSectionItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view.list.ProductsAdapter.Companion.PRODUCT_VIEW_TYPE
import ru.ozon.route256.feature_products_impl.presentation.view.list.ProductsAdapter.Companion.SECTION_VIEW_TYPE

object ViewHolderFactory {

    fun create(
        parent: ViewGroup,
        viewType: Int,
        clickAction: ProductClickAction,
        addToCartAction: AddToCartAction
    ): BaseListViewHolder<*> {
        return when (viewType) {
            PRODUCT_VIEW_TYPE -> ProductViewHolder(
                binding = ProductListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                clickAction = clickAction,
                addToCartAction = addToCartAction
            )
            SECTION_VIEW_TYPE -> SectionViewHolder(
                binding = ProductsSectionItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw Exception("ViewHolder for type $viewType not yet implemented")
        }
    }
}
