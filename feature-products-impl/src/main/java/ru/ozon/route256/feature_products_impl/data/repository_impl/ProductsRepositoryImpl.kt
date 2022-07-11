package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.SharedPreferences
import androidx.work.WorkManager
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.CartApi
import ru.ozon.route256.core_prefs.CacheKeys
import ru.ozon.route256.core_prefs.di.ProductsPrefs
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.core_utils.extensions.replace
import ru.ozon.route256.core_utils.workers.WorkerUtils
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    @ProductsPrefs private val localSource: SharedPreferences,
    private val moshi: Moshi,
    private val workManager: WorkManager,
    private val cart: CartApi
) : ProductsRepository {

    override fun getProductsFromCache(): List<ProductInListEntity> {
        val json = localSource.getString(CacheKeys.PRODUCTS.name, "")
        if (json.isNullOrEmpty()) return emptyList()

        val list = moshi.getListAdapter<ProductInListEntity>().fromJson(json)
        return list ?: emptyList()
    }

    override fun saveProductsInCache(productsJson: String) {
        localSource.edit()
            .putString(CacheKeys.PRODUCTS.name, productsJson)
            .commit()
    }

    override fun updateProductsInCache(productslist: List<ProductInListEntity>) = saveProductsInCache(
        moshi.getListAdapter<ProductInListEntity>()
            .toJson(productslist)
    )

    override fun requestProducts() {
        val productsRequest = WorkerUtils.getOneTimeRequest(
            clazz = GetProductsWorker::class.java,
            tag = GetProductsWorker.PRODUCTS_TAG
        )
        val detailedProductsRequest = WorkerUtils.getOneTimeRequest(
            clazz = GetDetailedProductsWorker::class.java,
            tag = GetDetailedProductsWorker.PRODUCTS_DETAILED_TAG
        )

        workManager
            .beginWith(productsRequest)
            .then(detailedProductsRequest)
            .enqueue()
    }

    override fun addToCart(guid: String, count: Int) {
        cart.addToCart(guid, count)

        val products = getProductsFromCache()
        val productToUpdate = products.first { it.guid == guid }
        val updatedProducts = (products as MutableList<ProductInListEntity>)
            .replace(
                oldItem = productToUpdate,
                newItem = productToUpdate.copy(isInCart = count > 0)
            )
        updateProductsInCache(updatedProducts)
    }
}
