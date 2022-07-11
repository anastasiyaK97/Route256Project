package ru.ozon.route256.feature_products_impl.presentation.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ozon.route256.core_utils.R
import ru.ozon.route256.feature_products_impl.databinding.ImageHolderViewBinding

class ImagesAdapter(
    private var images: List<String> = emptyList()
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    companion object {
        const val IMAGE_VIEW_TYPE = 999
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            view = ImageHolderViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(image = images.get(position))
    }

    override fun getItemCount(): Int = images.size

    override fun getItemViewType(position: Int): Int = IMAGE_VIEW_TYPE

    fun submitList(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val view: ImageView) : RecyclerView.ViewHolder(view) {
        fun bind(image: String) {
            Glide.with(view.context)
                .load(image)
                .error(R.color.grey_error_placeholder)
                .into(view)
        }
    }
}
