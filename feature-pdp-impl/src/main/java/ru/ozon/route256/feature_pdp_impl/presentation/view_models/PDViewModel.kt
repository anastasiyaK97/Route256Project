package ru.ozon.route256.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractor
import ru.ozon.route256.feature_pdp_impl.domain.model.Product
import javax.inject.Inject

class PDViewModel @Inject constructor(
    private val interactor: ProductInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<Product>()
    val productLD: LiveData<Product> = _productLD

    private var productId: String? = null

    fun setProductId(value: String?) {
        this.productId = value
    }

    fun loadData() {
        _productLD.value = productId?.let { interactor.getProductById(it) }
    }

}
