package ru.ozon.route256.feature_pdp_impl.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import ru.ozon.route256.core_utils.R
import ru.ozon.route256.feature_pdp_impl.databinding.ViewEditProductCountButtonBinding

typealias DefaultCountCartAction = (Int) -> Unit

class EditProductCountButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewEditProductCountButtonBinding.inflate(LayoutInflater.from(context), this)

    private var isStepperState: Boolean = false
        set(value) {
            field = value
            setCartButtonVisibility(isShowDefaultButtons = !value)
        }

    var isLoading: Boolean = false
        set(value) {
            field = value
            binding.run {
                progressBar.isVisible = value
                buttonText.isVisible = !value && isStepperState
                countText.isVisible = !value && isStepperState
                defaultButtonText.isVisible = !value && !isStepperState

                addProductButton.isEnabled = !value
                removeProductButton.isEnabled = !value
            }
        }

    var countInCart: Int = 0
        set(value) {
            field = value
            if (value >= 1) isStepperState = true
            value.let(this::setupCountInfo)
        }

    private var addToCardAction: DefaultCountCartAction? = null
    private var removeFromCardAction: DefaultCountCartAction? = null

    init {
        binding.addProductButton.setOnClickListener { addToCardAction?.invoke(countInCart + 1) }
        binding.removeProductButton.setOnClickListener { removeFromCardAction?.invoke(countInCart - 1) }
        binding.defaultButtonText.setOnClickListener { addToCardAction?.invoke(countInCart + 1) }
        setCartButtonVisibility(isShowDefaultButtons = countInCart == 0)
    }

    fun setupCartActions(
        addAction: DefaultCountCartAction,
        removeAction: DefaultCountCartAction
    ) {
        addToCardAction = addAction
        removeFromCardAction = removeAction
    }

    private fun setupCountInfo(countInCart: Int) {
        binding.run {
            countText.text = countInCart.toString()
            removeProductButton.imageTintList = context.getColorStateList(
                if (countInCart <= 1) R.color.grey else R.color.dark_text
            )
            removeProductButton.isClickable = countInCart > 1
        }
    }

    private fun setCartButtonVisibility(isShowDefaultButtons: Boolean) {
        binding.defaultButtonText.isVisible = isShowDefaultButtons
        binding.productCountGroup.isVisible = !isShowDefaultButtons
        val backgroundRes = when (isShowDefaultButtons) {
            true -> R.drawable.background_cart_button_default
            else -> ru.ozon.route256.feature_pdp_impl.R.drawable.background_add_to_cart_button_gray
        }
        setBackgroundResource(backgroundRes)
    }
}
