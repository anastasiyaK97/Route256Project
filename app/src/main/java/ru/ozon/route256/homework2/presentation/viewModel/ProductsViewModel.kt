package ru.ozon.route256.homework2.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.homework2.domain.interactors.ProductsInteractor
import ru.ozon.route256.homework2.presentation.viewObject.ProductInList

class ProductsViewModel(
    private val interactor: ProductsInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<List<ProductInList>>()
    val productLD: LiveData<List<ProductInList>> = _productLD

    init {
        updateCurrentState()
    }

    fun increaseVisitorCounter(productId: String) {
        interactor.increaseProductCounter(productId)
        updateCurrentState()
    }

    private fun updateCurrentState() {
        _productLD.value = interactor.getProducts()
    }
}
