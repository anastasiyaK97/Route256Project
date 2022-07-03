package ru.ozon.route256.feature_products_impl.presentation.view_holders

import com.bumptech.glide.Glide
import ru.ozon.route256.core_utils.R
import ru.ozon.route256.core_utils.extensions.withCurrency
import ru.ozon.route256.core_utils.view.setTextOrGone
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view.CartButton
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem

typealias ProductClickAction = (String) -> Unit
typealias AddToCartAction = (String) -> Unit

class ProductViewHolder(
    private val binding: ProductListItemBinding,
    clickAction: ProductClickAction,
    addToCartAction: AddToCartAction
) : BaseListViewHolder<ProductsListItem.ProductInList>(binding) {

    private var product: ProductsListItem.ProductInList? = null

    init {
        binding.root.setOnClickListener {
            product?.let { clickAction.invoke(it.guid) }
        }
        binding.cartButton.setOnClickListener {
            product?.let { addToCartAction.invoke(it.guid) }
        }
    }

    override fun bindItem(item: ProductsListItem.ProductInList) {
        product = item

        with(binding) {
            nameTV.text = item.name
            priceTV.text = item.price.withCurrency()
            ratingView.rating = item.rating.toFloat()

            visitorCounter.setTextOrGone(item.counter.takeIf { it > 0 }?.toString())
            cartButton.isLoading = item.isLoading
            cartButton.buttonState = if (item.isInCart) CartButton.CartButtonState.DONE else CartButton.CartButtonState.DEFAULT

            Glide.with(itemView.context)
                .load(item.image)
                .error(R.color.grey_error_placeholder)
                .into(productIV)

        }
    }
}