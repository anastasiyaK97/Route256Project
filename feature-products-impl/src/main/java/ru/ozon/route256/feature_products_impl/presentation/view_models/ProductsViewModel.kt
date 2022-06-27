package ru.ozon.route256.feature_products_impl.presentation.view_models

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.mapper.toProductInList
import ru.ozon.route256.feature_products_impl.presentation.view_objects.ProductInList
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val interactor: ProductsInteractor
) : ViewModel(), DefaultLifecycleObserver {

    companion object {
        private const val UPDATING_INTERVAL_MIN = 5L
    }

    private val _productLD = MutableLiveData<List<ProductInList>>()
    val productLD: LiveData<List<ProductInList>> = _productLD

    private val compositeDisposable = CompositeDisposable()

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

}
