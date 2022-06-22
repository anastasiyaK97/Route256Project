package ru.ozon.route256.feature_products_impl.domain.interactor

import androidx.work.WorkManager
import ru.ozon.route256.core_utils.extensions.replace
import ru.ozon.route256.core_utils.workers.WorkerUtils
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetDetailedProductsWorker
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetProductsWorker
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsInteractorImpl @Inject constructor(
    private val repository: ProductsRepository
): ProductsInteractor {

    private val workManager = WorkManager.getInstance()

    override fun planProductsRequest() {
        val productsRequest = WorkerUtils.getOneTimeRequest(GetProductsWorker::class.java, WorkerUtils.PRODUCTS_TAG)
        val detailedProductsRequest = WorkerUtils.getOneTimeRequest(GetDetailedProductsWorker::class.java, WorkerUtils.PRODUCTS_DETAILED_TAG)

        workManager
            .beginWith(productsRequest)
            .then(detailedProductsRequest)
            .enqueue()
    }

    override fun getProducts(): List<ProductInListEntity> =
        repository.getProductsFromCache()

    override fun increaseProductCounter(guid: String) {
        val productToReplace = getProducts().find { it.guid == guid } ?: return
        val newCount = (productToReplace.counter ?: 0) + 1
        val products = getProducts().toMutableList().replace(
            oldItem = productToReplace,
            newItem = productToReplace.copy(counter = newCount)
        )
        repository.updateProductsInCache(products)
    }

}
