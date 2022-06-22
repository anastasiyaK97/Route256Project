package ru.ozon.route256.core_network_api

import retrofit2.Call
import retrofit2.http.GET
import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.core_network_api.model.product.ProductInListDTO

interface ProductsApi {

    @GET("/v3/50afcd4b-d81e-473e-827c-1b6cae1ea1b2/")
    fun getProducts(): Call<List<ProductInListDTO>>

    @GET("/v3/8c374376-e94e-4c5f-aa30-a9eddb0d7d0a/")
    fun getDetailedProducts(): Call<List<ProductDTO>>
}
