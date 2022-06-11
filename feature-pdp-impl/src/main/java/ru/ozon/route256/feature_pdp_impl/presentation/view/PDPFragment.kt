package ru.ozon.route256.feature_pdp_impl.presentation.view

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
import ru.ozon.route256.feature_pdp_impl.R
import ru.ozon.route256.feature_pdp_impl.databinding.PdpFragmentBinding
import ru.ozon.route256.feature_pdp_impl.presentation.view_models.PDViewModel
import ru.ozon.route256.feature_pdp_impl.presentation.view_object.Product

class PDPFragment : Fragment() {

    companion object {
        const val ID_KEY = "ID_KEY"

        fun newInstance(id: String): PDPFragment {
            val fragment = PDPFragment()
            fragment.arguments = (fragment.arguments ?: bundleOf()).also {
                it.putString(ID_KEY, id)
            }
            return fragment
        }
    }

    private val binding: PdpFragmentBinding by viewBinding(PdpFragmentBinding::bind)
    private val viewModel: PDViewModel by viewModelCreator {
        PDViewModel(id = requireNotNull(arguments?.getString(ID_KEY))
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.pdp_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productLD.observe(viewLifecycleOwner) { product ->
            renderState(product)
        }
    }

    private fun renderState(product: Product) = with(binding) {
        nameTV.text = product.name
        descriptionTV.text = product.description
        priceTV.text = product.price.withCurrency()
        ratingView.rating = product.rating.toFloat()

        Glide.with(requireContext())
            .load(product.images.getOrElse(0) { "" })
            .error(ru.ozon.route256.core_utils.R.color.grey_error_placeholder)
            .into(productIV)
    }

}
