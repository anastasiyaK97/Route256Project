package ru.ozon.route256.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.WorkManager
import ru.ozon.route256.core_utils.view.viewBinding
import ru.ozon.route256.core_utils.view_models.viewModelCreator
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetProductsWorker
import ru.ozon.route256.feature_products_impl.databinding.FragmentProductsBinding
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.view_models.ProductsViewModel
import javax.inject.Inject

class ProductsFragment : Fragment() {

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

    private val binding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(interactor)
    }

    private val recyclerAdapter = ProductsAdapter(emptyList(), ::holderClickAction)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureProductsComponent.featureProductComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_products, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = recyclerAdapter
        viewModel.productLD.observe(viewLifecycleOwner) { recyclerAdapter.submitList(it) }

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

}
