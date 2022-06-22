package ru.ozon.route256.feature_products_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.mapper.toProductInList
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val interactor: ProductsInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<List<ProductInList>>()
    val productLD: LiveData<List<ProductInList>> = _productLD

    init {
        interactor.planProductsRequest()
    }

    fun increaseVisitorCounter(productId: String) {
        interactor.increaseProductCounter(productId)
        updateCurrentState()
    }

    fun updateCurrentState() {
        _productLD.value = interactor.getProducts().map { it.toProductInList() }
    }
}
