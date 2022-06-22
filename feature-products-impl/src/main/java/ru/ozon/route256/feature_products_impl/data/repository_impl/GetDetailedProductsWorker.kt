package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.Context
import android.content.SharedPreferences
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.core_prefs.CacheKeys
import ru.ozon.route256.core_prefs.di.ProductsDetailsPrefs
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent
import javax.inject.Inject

class GetDetailedProductsWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    @Inject
    lateinit var productsApi: ProductsApi

    @Inject
    lateinit var moshi: Moshi

    @Inject
    @ProductsDetailsPrefs
    lateinit var prefs: SharedPreferences

    override fun doWork(): Result {
        FeatureProductsComponent.featureProductComponent?.inject(this)

        val response = productsApi.getDetailedProducts().execute()
        return if (response.isSuccessful) {
            val json = moshi.getListAdapter<ProductDTO>().toJson(response.body())
            prefs.edit()
                .putString(CacheKeys.PRODUCTS_DETAIL.key, json)
                .apply()
            Result.success()
        } else {
            Result.retry()
        }
    }

}
