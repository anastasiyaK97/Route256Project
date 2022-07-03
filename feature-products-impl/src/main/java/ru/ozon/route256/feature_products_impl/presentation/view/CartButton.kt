package ru.ozon.route256.feature_products_impl.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.ViewCartButtonBinding

class CartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewCartButtonBinding.inflate(LayoutInflater.from(context), this)

    var isLoading: Boolean = false
        set(value) {
            field = value
            with(binding) {
                buttonText.isVisible = !value
                progressBar.isVisible = value
                root.isEnabled = !value
            }
        }

    var buttonState: CartButtonState = CartButtonState.DEFAULT
        set(value) {
            field = value
            updateButtonState(state = value)
        }

    init {
        updateButtonState(CartButtonState.DEFAULT)
    }

    private fun updateButtonState(state: CartButtonState) {
        binding.root.background = ContextCompat.getDrawable(context, state.backgroundRes)
        binding.buttonText.setText(state.text)
    }

    enum class CartButtonState(
        @DrawableRes val backgroundRes: Int,
        @StringRes val text: Int
    ) {
        DEFAULT(R.drawable.background_cart_button_default, ru.ozon.route256.core_utils.R.string.cart_title_default),
        DONE(R.drawable.background_cart_button_done, ru.ozon.route256.core_utils.R.string.cart_title_done)
    }
}
