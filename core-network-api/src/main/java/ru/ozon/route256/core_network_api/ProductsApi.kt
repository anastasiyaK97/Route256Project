package ru.ozon.route256.core_network_api

import io.reactivex.Single
import retrofit2.http.GET
import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.core_network_api.model.product.ProductInListDTO

interface ProductsApi {

    @GET("/v3/ee6876a1-8c02-45aa-bde4-b91817a8b210/")
    fun getProducts(): Single<List<ProductInListDTO>>

    @GET("/v3/8c374376-e94e-4c5f-aa30-a9eddb0d7d0a/")
    fun getDetailedProducts(): Single<List<ProductDTO>>
}
