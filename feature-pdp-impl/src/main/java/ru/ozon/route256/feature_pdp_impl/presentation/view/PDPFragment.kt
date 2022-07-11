package ru.ozon.route256.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.ozon.route256.core_utils.extensions.withCurrency
import ru.ozon.route256.core_utils.view.viewBinding
import ru.ozon.route256.core_utils.view_models.viewModelCreator
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.R
import ru.ozon.route256.feature_pdp_impl.databinding.PdpFragmentBinding
import ru.ozon.route256.feature_pdp_impl.di.FeaturePDPComponent
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductDetailModel
import ru.ozon.route256.feature_pdp_impl.presentation.view_models.PDViewModel
import javax.inject.Inject

class PDPFragment : Fragment() {

    companion object {
        const val TAG = "PDPFragment"
        const val ID_KEY = "ID_KEY"

        fun newInstance(id: String): PDPFragment {
            val fragment = PDPFragment()
            fragment.arguments = (fragment.arguments ?: bundleOf()).also {
                it.putString(ID_KEY, id)
            }
            return fragment
        }
    }

    @Inject
    lateinit var productNavigation: PDPNavigationApi

    @Inject
    lateinit var interactor: ProductInteractor

    private val binding: PdpFragmentBinding by viewBinding(PdpFragmentBinding::bind)
    private val viewModel: PDViewModel by viewModelCreator {
        PDViewModel(interactor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeaturePDPComponent.featurePDPComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setProductId(arguments?.getString(ID_KEY))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.pdp_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productLD.observe(viewLifecycleOwner) { product ->
            renderState(product)
        }
        viewModel.loadData()

        binding.editCountButton.setupCartActions(
            addAction = { newCount -> viewModel.addProductToCart(newCount) },
            removeAction = { newCount -> viewModel.removeProductFromCart(newCount) }
        )
    }

    override fun onPause() {
        if (isRemoving) {
            if (productNavigation.isFeatureProductDetailsClosed(this)) {
                FeaturePDPComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun renderState(productModel: ProductDetailModel) = with(binding) {
        nameTV.text = productModel.product.name
        descriptionTV.text = productModel.product.description
        priceTV.text = productModel.product.price.withCurrency()
        ratingView.rating = productModel.product.rating.toFloat()

        editCountButton.countInCart = productModel.countInCart
        editCountButton.isLoading = productModel.isLoading

        Glide.with(requireContext())
            .load(productModel.product.images.getOrElse(0) { "" })
            .error(ru.ozon.route256.core_utils.R.color.grey_error_placeholder)
            .into(productIV)
    }
}
