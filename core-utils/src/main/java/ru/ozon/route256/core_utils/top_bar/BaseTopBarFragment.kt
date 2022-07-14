package ru.ozon.route256.core_utils.top_bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import ru.ozon.route256.core_utils.R

abstract class BaseTopBarFragment(
    @LayoutRes private val layout: Int
): Fragment() {

    private var _currentFragment: View? = null
    protected val currentFragment: View
        get() = _currentFragment ?: throw IllegalStateException("View is not created yet")

    protected var animatedTopBar: AnimatedTopBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = inflater.inflate(R.layout.fragment_base_tob_bar, container, false)
        animatedTopBar = parentView.findViewById(R.id.animated_top_bar)

        val scrollContainer = parentView.findViewById<NestedScrollView>(R.id.scroll_container)
        _currentFragment = inflater.inflate(layout, scrollContainer, false)
        scrollContainer?.addView(_currentFragment)

        return parentView
    }

}
