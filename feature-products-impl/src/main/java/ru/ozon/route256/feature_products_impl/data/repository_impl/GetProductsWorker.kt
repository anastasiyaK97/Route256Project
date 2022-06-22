package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.model.product.ProductInListDTO
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.feature_products_impl.data.mapper.toProductInListEntity
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductsWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    @Inject
    lateinit var productsApi: ProductsApi

    @Inject
    lateinit var repository: ProductsRepository

    @Inject
    lateinit var moshi: Moshi

    override fun doWork(): Result {
        FeatureProductsComponent.featureProductComponent?.inject(this)
        val response = productsApi.getProducts().execute()

        return if (response.isSuccessful) {
            val json = moshi.getListAdapter<ProductInListEntity>().toJson(
                response.body()?.map { it.toProductInListEntity() }
            )

            repository.saveProductsInCache(json)
            Result.success()
        } else {
            Result.retry()
        }
    }

}
