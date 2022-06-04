package ru.ozon.route256.homework2.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ozon.route256.homework2.R
import ru.ozon.route256.homework2.databinding.FragmentProductsBinding
import ru.ozon.route256.homework2.di.ServiceLocator
import ru.ozon.route256.homework2.presentation.adapters.ProductsAdapter
import ru.ozon.route256.homework2.presentation.view.utils.viewBinding
import ru.ozon.route256.homework2.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.homework2.presentation.viewModel.viewModelCreator

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val binding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(ServiceLocator().productsInteractor)
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

        viewModel.increaseVisitorCounter(id)

        val nextFragment = PDPFragment.newInstance(id)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, nextFragment, null)
            .addToBackStack(null)
            .commit()
    }

}
