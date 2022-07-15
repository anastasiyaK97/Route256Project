package ru.ozon.route256.core_utils.top_bar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.google.android.material.appbar.AppBarLayout
import ru.ozon.route256.core_utils.databinding.AnimatedAppBarBinding
import ru.ozon.route256.core_utils.view.px

class AnimatedTopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppBarLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    companion object {
        private const val START_ANIMATE_OFFSET = 0F
        private const val SEARCH_HORIZONTAL_MARGIN_DP = 16
        private const val COLLAPSED_SEARCH_END_MARGIN_DP = 68

        private const val EXPAND_ICON_SIZE_DP = 32
        private const val VERTICAL_ICON_OFFSET_DP = 12
    }

    private val binding = AnimatedAppBarBinding.inflate(LayoutInflater.from(context), this)

    private var isCalculated = false
    private var expandedSearchWidthPx: Float = 0F

    init {
        addOnOffsetChangedListener(this)
    }

    fun init(
        title: String,
        isSearchInputVisible: Boolean = false
    ) {
        binding.titleText.text = title
        binding.searchInput.isVisible = isSearchInputVisible
    }

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (isCalculated.not()) {
            expandedSearchWidthPx = binding.searchInput.width.toFloat()
            isCalculated = true
        }
        updateViews(Math.abs(verticalOffset / appBar.totalScrollRange.toFloat()))
    }

    private fun updateViews(offset: Float) {
        if (binding.searchInput.isVisible) {
            animateTitle(offset)
            animateIcon(offset)
            animateSearchInput(offset)
        }
    }

    private fun animateTitle(offset: Float) {
        when (offset) {
            in 0.15F..1F -> binding.titleText.alpha = 1 - offset
            else -> binding.titleText.alpha = 1f
        }
    }

    private fun animateSearchInput(offset: Float) {
        binding.searchInput.apply {
            when {
                offset > START_ANIMATE_OFFSET -> {
                    val marginEnd = SEARCH_HORIZONTAL_MARGIN_DP.px -
                        (SEARCH_HORIZONTAL_MARGIN_DP.px * 2 - COLLAPSED_SEARCH_END_MARGIN_DP.px) * offset
                    val newWidth = expandedSearchWidthPx - marginEnd
                    updateLayoutParams<ViewGroup.LayoutParams> { width = Math.round(newWidth) }
                }
                else -> if (width < expandedSearchWidthPx) {
                    updateLayoutParams<ViewGroup.LayoutParams> { width = Math.round(expandedSearchWidthPx) }
                }
            }
        }
    }

    private fun animateIcon(offset: Float) {
        binding.shareImage.apply {
            when {
                offset > START_ANIMATE_OFFSET -> {
                    val iconSize = EXPAND_ICON_SIZE_DP.px - (EXPAND_ICON_SIZE_DP.px) * offset
                    updateLayoutParams<ViewGroup.LayoutParams> {
                        width = Math.round(iconSize)
                        height = Math.round(iconSize)
                    }
                    translationY = ((binding.pinned.height - VERTICAL_ICON_OFFSET_DP.px - iconSize) / 2) * offset
                    alpha = 1 - offset
                }
                else -> if (width < EXPAND_ICON_SIZE_DP.px) {
                    updateLayoutParams<ViewGroup.LayoutParams> {
                        width = EXPAND_ICON_SIZE_DP.px
                        height = EXPAND_ICON_SIZE_DP.px
                    }
                    translationY = 0F
                    alpha = 1F
                }
            }
        }
    }
}
