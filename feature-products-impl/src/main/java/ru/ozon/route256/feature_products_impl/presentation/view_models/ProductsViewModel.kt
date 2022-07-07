package ru.ozon.route256.feature_products_impl.presentation.view_models

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.ozon.route256.core_utils.extensions.replace
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.mapper.toProductsItemList
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductsListItem
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val interactor: ProductsInteractor
) : ViewModel(), DefaultLifecycleObserver {

    companion object {
        private const val UPDATING_INTERVAL_MIN = 5L
    }

    private val _productLD = MutableLiveData<List<ProductsListItem>>()
    val productLD: LiveData<List<ProductsListItem>> = _productLD

    private val compositeDisposable = CompositeDisposable()

    init {
        interactor.planProductsRequest()
    }

    fun increaseVisitorCounter(productId: String) {
        interactor.increaseProductCounter(productId)
        updateCurrentState()
    }

    fun updateCurrentState() {
        _productLD.value = interactor.getProducts().toProductsItemList()
    }

    override fun onStart(owner: LifecycleOwner) {
        compositeDisposable += Observable
            .interval(UPDATING_INTERVAL_MIN, TimeUnit.MINUTES)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = { interactor.planProductsRequest() }
            )
    }

    override fun onStop(owner: LifecycleOwner) {
        compositeDisposable.clear()
    }

    fun addProductToCart(id: String) {
        updateProductState(id = id, isLoading = true)

        //Imitation of sending a network request for show loading
        compositeDisposable += Completable
            .timer(1L, TimeUnit.SECONDS, Schedulers.io())
            .subscribeBy(
                onComplete = {
                    interactor.addToCart(id, count = 1)
                    updateProductState(id = id, isLoading = false, isInCart = true)
                },
                onError = {
                    updateProductState(id = id, isLoading = false, isInCart = false)
                }
            )
    }

    private fun updateProductState(
        id: String,
        isLoading: Boolean = false,
        isInCart: Boolean = false
    ) {
        _productLD.value?.let { items ->
            val product = items
                .find { it is ProductsListItem.ProductInList && it.guid == id }
                    as? ProductsListItem.ProductInList
                ?: return
            _productLD.postValue(
                items.toMutableList()
                    .replace(
                        oldItem = product,
                        newItem = product.copy(isLoading = isLoading, isInCart = isInCart)
                    )
            )
        }
    }

}
