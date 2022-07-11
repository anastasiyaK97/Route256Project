package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import io.reactivex.Single
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.feature_products_impl.data.mapper.toProductInListEntity
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductsWorker(
    context: Context,
    workerParameters: WorkerParameters
) : RxWorker(context, workerParameters) {

    companion object {
        const val PRODUCTS_TAG = "TAG_PRODUCTS"
    }

    @Inject
    lateinit var productsApi: ProductsApi

    @Inject
    lateinit var repository: ProductsRepository

    @Inject
    lateinit var moshi: Moshi

    init {
        FeatureProductsComponent.featureProductComponent?.inject(this)
    }

    override fun createWork(): Single<Result> {
        return productsApi.getProducts()
            .map { response ->
                val json = moshi.getListAdapter<ProductInListEntity>().toJson(
                    response.map { it.toProductInListEntity() }
                )
                repository.saveProductsInCache(json)
                Result.success()
            }
            .onErrorReturn { Result.failure() }
    }
}
