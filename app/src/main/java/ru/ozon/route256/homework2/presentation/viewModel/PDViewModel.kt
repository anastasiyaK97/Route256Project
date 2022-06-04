package ru.ozon.route256.homework2.presentation.viewModel

import Product
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ozon.route256.homework2.domain.interactors.ProductsInteractor

class PDViewModel(
    private val id: String,
    private val interactor: ProductsInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<Product>()
    val productLD: LiveData<Product> = _productLD

    init {
        _productLD.value = interactor.getProductById(id)
    }

}
