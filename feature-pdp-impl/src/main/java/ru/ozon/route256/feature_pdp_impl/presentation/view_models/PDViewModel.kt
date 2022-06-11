package ru.ozon.route256.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.view_object.Product

class PDViewModel(
    private val id: String,
    private val interactor: ProductInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<Product>()
    val productLD: LiveData<Product> = _productLD

    init {
        _productLD.value = interactor.getProductById(id)
    }

}
