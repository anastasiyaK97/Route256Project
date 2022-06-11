package ru.ozon.route256.feature_products_impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ozon.route256.core_utils.view.viewBinding
import ru.ozon.route256.core_utils.view_models.viewModelCreator
import ru.ozon.route256.feature_products_api.navigation.FeatureProductsNavigationApi
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.FragmentProductsBinding
import ru.ozon.route256.feature_products_impl.presentation.view_models.ProductsViewModel

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    lateinit var productsNavigation: FeatureProductsNavigationApi

    private val binding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel()
    }

    private val recyclerAdapter = ProductsAdapter(emptyList(), ::holderClickAction)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_products, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = recyclerAdapter
        viewModel.productLD.observe(viewLifecycleOwner) { recyclerAdapter.submitList(it) }
    }

    private fun holderClickAction(id: String) {
        viewModel.increaseVisitorCounter(productId = id)

        productsNavigation.openProductDetailScreen(productId = id)
        /*

        val nextFragment = PDPFragment.newInstance(id)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, nextFragment, null)
            .addToBackStack(null)
            .commit()*/
    }

}
