package ru.ozon.route256.feature_products_impl.presentation.view_holders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ozon.route256.core_utils.extensions.withCurrency
import ru.ozon.route256.core_utils.view.setTextOrGone
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view.CartButton
import ru.ozon.route256.feature_products_impl.presentation.view.list.ImagesAdapter
import ru.ozon.route256.feature_products_impl.presentation.view.list.ProductPayload
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

typealias ProductClickAction = (String) -> Unit
typealias AddToCartAction = (String) -> Unit

class ProductViewHolder(
    private val binding: ProductListItemBinding,
    clickAction: ProductClickAction,
    addToCartAction: AddToCartAction,
    sharedViewPool: RecyclerView.RecycledViewPool
) : BaseListViewHolder<ProductsListItem.ProductInList>(binding) {

    private val adapter = ImagesAdapter()
    private var product: ProductsListItem.ProductInList? = null

    init {
        with(binding) {
            root.setOnClickListener {
                product?.let { clickAction.invoke(it.guid) }
            }
            cartButton.setOnClickListener {
                product?.let { addToCartAction.invoke(it.guid) }
            }

            productIV.adapter = adapter
            productIV.layoutManager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            productIV.setRecycledViewPool(sharedViewPool)
        }
    }

    override fun bindItem(item: ProductsListItem.ProductInList, payloads: MutableList<Any>?) {
        if (payloads.isNullOrEmpty()) {
            bindProductInfo(item)
        } else {
            val payload = payloads[0] as ProductPayload
            updateProduct(payload)
        }
    }

    private fun bindProductInfo(item: ProductsListItem.ProductInList) {
        product = item

        with(binding) {
            nameTV.text = item.name
            priceTV.text = item.price.withCurrency()
            ratingView.rating = item.rating.toFloat()

            visitorCounter.setTextOrGone(item.counter.takeIf { it > 0 }?.toString())
            updateButton(item.isLoading, item.isInCart)

            adapter.submitList(item.images)
            /*Glide.with(itemView.context)
                    .load(item.images)
                    .error(R.color.grey_error_placeholder)
                    .into(productIV)*/

        }
    }

    private fun updateProduct(payload: ProductPayload) {
        updateButton(payload.isLoading, payload.isInCart)
    }

    private fun updateButton(isLoading: Boolean, isInCart: Boolean) {
        binding.cartButton.isLoading = isLoading
        binding.cartButton.buttonState = if (isInCart) {
            CartButton.CartButtonState.DONE
        } else {
            CartButton.CartButtonState.DEFAULT
        }
    }
}