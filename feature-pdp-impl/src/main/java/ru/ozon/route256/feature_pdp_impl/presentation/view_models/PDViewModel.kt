package ru.ozon.route256.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductDetailModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PDViewModel @Inject constructor(
    private val interactor: ProductInteractor
) : ViewModel() {

    private val _productLD = MutableLiveData<ProductDetailModel>()
    val productLD: LiveData<ProductDetailModel> = _productLD
    private val compositeDisposable = CompositeDisposable()

    private var productId: String? = null

    fun setProductId(value: String?) {
        this.productId = value
    }

    fun loadData() {
        productId?.let { productId ->
            interactor.getProductById(productId)?.let { product ->
                _productLD.value = _productLD.value
                    ?.copy(product = product)
                    ?: ProductDetailModel(product = product)
            }
        }
    }

    fun addProductToCart(newCount: Int) {
        updateProductState(isLoading = true)
        compositeDisposable += Completable
            .timer(1L, TimeUnit.SECONDS, Schedulers.io())
            .subscribeBy(
                onComplete = {
                    updateProductState(isLoading = false, countInCart = newCount)
                },
                onError = {
                    updateProductState(isLoading = false, countInCart = newCount - 1)
                }
            )
    }

    fun removeProductFromCart(newCount: Int) {
        updateProductState(isLoading = true)
        compositeDisposable += Completable
            .timer(1L, TimeUnit.SECONDS, Schedulers.io())
            .subscribeBy(
                onComplete = {
                    updateProductState(isLoading = false, countInCart = newCount)
                },
                onError = {
                    updateProductState(isLoading = false, countInCart = newCount + 1)
                }
            )
    }

    private fun updateProductState(isLoading: Boolean, countInCart: Int? = null) {
        var updatedProduct = _productLD.value?.copy(isLoading = isLoading) ?: return
        countInCart?.let { updatedProduct = updatedProduct.copy(countInCart = it) }
        _productLD.postValue(updatedProduct)
    }

}
