package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.core_prefs.CacheKeys
import ru.ozon.route256.core_prefs.di.ProductsDetailsPrefs
import ru.ozon.route256.core_utils.extensions.getListAdapter
import ru.ozon.route256.feature_pdp_impl.data.mapper.toProduct
import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository
import ru.ozon.route256.feature_pdp_impl.domain.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    @ProductsDetailsPrefs private val cache: SharedPreferences,
    private val moshi: Moshi
) : ProductRepository {

    override fun getProductById(guid: String): Product? {
        val json = cache.getString(CacheKeys.PRODUCTS_DETAIL.name, "")
        if (json.isNullOrEmpty()) return null

        val list = moshi.getListAdapter<ProductDTO>().fromJson(json)
        return list?.firstOrNull { it.guid == guid }?.toProduct()
    }
}