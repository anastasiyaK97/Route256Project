package ru.ozon.route256.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.work.WorkManager
import ru.ozon.route256.core_utils.top_bar.BaseTopBarFragment
import ru.ozon.route256.core_utils.view.viewBinding
import ru.ozon.route256.core_utils.view_models.viewModelCreator
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetProductsWorker
import ru.ozon.route256.feature_products_impl.databinding.FragmentProductsBinding
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.view.list.ProductsAdapter
import ru.ozon.route256.feature_products_impl.presentation.view_models.ProductsViewModel
import javax.inject.Inject

class ProductsFragment : BaseTopBarFragment(
    layout = R.layout.fragment_products
) {

    companion object {
        const val TAG = "ProductsFragment"

        fun newInstance() = ProductsFragment()
    }

    @Inject
    lateinit var productsNavigation: ProductsNavigationApi

    @Inject
    lateinit var interactor: ProductsInteractor

    @Inject
    lateinit var workManager: WorkManager

    private val binding: FragmentProductsBinding by viewBinding {
        FragmentProductsBinding.bind(currentFragment)
    }
    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(interactor)
    }

    private val recyclerAdapter = ProductsAdapter(
        clickAction = ::holderClickAction,
        addToCartAction = ::addToCartAction
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureProductsComponent.featureProductComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animatedTopBar?.init(
            title = requireContext().getString(ru.ozon.route256.core_utils.R.string.catalog_title),
            isSearchInputVisible = true
        )

        binding.list.adapter = recyclerAdapter
        viewModel.productLD.observe(viewLifecycleOwner) { recyclerAdapter.submitList(it) }
        lifecycle.addObserver(viewModel)

        listenToWorkerState(GetProductsWorker.PRODUCTS_TAG)
    }

    override fun onPause() {
        if (isRemoving) {
            if (productsNavigation.isFeatureProductsClosed(this)) {
                FeatureProductsComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun listenToWorkerState(tag: String) {
        workManager.getWorkInfosByTagLiveData(tag)
            .observe(viewLifecycleOwner) { workInfoList ->
                if (workInfoList.isEmpty()) return@observe
                if (workInfoList.first().state.isFinished) {
                    updateState()
                }
            }
    }

    private fun updateState() {
        viewModel.updateCurrentState()
    }

    private fun holderClickAction(id: String) {
        viewModel.increaseVisitorCounter(productId = id)
        productsNavigation.openPDPScreen(fragment = this, productId = id)
    }

    private fun addToCartAction(id: String) {
        viewModel.addProductToCart(id)
    }
}
