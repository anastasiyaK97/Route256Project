package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_prefs.CacheKeys
import ru.ozon.route256.core_prefs.di.ProductsPrefs
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
    @ProductsPrefs private val localSource: SharedPreferences,
    private val moshi: Moshi
) : ProductsRepository {

    override fun getProductsFromCache(): List<ProductInListEntity> {
        val json = localSource.getString(CacheKeys.PRODUCTS.key, "")
        if (json.isNullOrEmpty()) return emptyList()

        val list = moshi.getListAdapter<ProductInListEntity>().fromJson(json)
        return list ?: emptyList()
    }

    override fun saveProductsInCache(productsJson: String) {
        localSource.edit()
            .putString(CacheKeys.PRODUCTS.key, productsJson)
            .commit()
    }

    override fun updateProductsInCache(productslist: List<ProductInListEntity>) = saveProductsInCache(
        moshi.getListAdapter<ProductInListEntity>()
            .toJson(productslist)
    )


}
