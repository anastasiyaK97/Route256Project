package ru.ozon.route256.feature_products_impl.presentation.view.list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.ozon.route256.feature_products_impl.presentation.view.list.ImagesAdapter.Companion.IMAGE_VIEW_TYPE
import ru.ozon.route256.feature_products_impl.presentation.view_holders.AddToCartAction
import ru.ozon.route256.feature_products_impl.presentation.view_holders.BaseListViewHolder
import ru.ozon.route256.feature_products_impl.presentation.view_holders.ProductClickAction
import ru.ozon.route256.feature_products_impl.presentation.view_holders.ViewHolderFactory
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

class ProductsAdapter(
    private val clickAction: ProductClickAction,
    private val addToCartAction: AddToCartAction
) : ListAdapter<ProductsListItem, BaseListViewHolder<*>>(DiffUtilProductsCallback()) {

    companion object {
        const val PRODUCT_VIEW_TYPE: Int = 10
        const val SECTION_VIEW_TYPE: Int = 20
    }

    private val imagesSharedViewPool = RecyclerView.RecycledViewPool().apply {
        this.setMaxRecycledViews(IMAGE_VIEW_TYPE, 20)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderFactory.create(
            parent = parent,
            viewType = viewType,
            clickAction = clickAction,
            addToCartAction = addToCartAction,
            imagesRecyclerViewPool = imagesSharedViewPool
        )

    override fun onBindViewHolder(
        holder: BaseListViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(getItem(position), payloads)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ProductsListItem.ProductInList -> PRODUCT_VIEW_TYPE
        is ProductsListItem.SectionListItem -> SECTION_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        holder.bind(getItem(position))
    }
}
