package ru.ozon.route256.homework2.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ozon.route256.homework2.R
import ru.ozon.route256.homework2.databinding.ProductListItemBinding
import ru.ozon.route256.homework2.presentation.extensions.setTextOrGone
import ru.ozon.route256.homework2.presentation.extensions.withCurrency
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}