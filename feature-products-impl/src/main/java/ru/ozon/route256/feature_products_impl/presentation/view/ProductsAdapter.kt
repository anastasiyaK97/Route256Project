package ru.ozon.route256.feature_products_impl.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ozon.route256.core_utils.extensions.withCurrency
import ru.ozon.route256.core_utils.view.setTextOrGone
import ru.ozon.route256.core_utils.R
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList

class ProductsAdapter(
    private var list: List<ProductInList>,
    private val clickAction: (String) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: ProductInList) {
            binding.nameTV.text = productItem.name
            binding.priceTV.text = productItem.price.withCurrency()
            binding.ratingView.rating = productItem.rating.toFloat()

            binding.visitorCounter.setTextOrGone(productItem.counter.takeIf { it > 0 }?.toString())

            Glide.with(itemView.context)
                .load(productItem.image)
                .error(R.color.grey_error_placeholder)
                .into(binding.productIV)

            binding.root.setOnClickListener { clickAction.invoke(productItem.guid) }
        }
    }

    fun submitList(updatedList: List<ProductInList>) {
        list = updatedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}
