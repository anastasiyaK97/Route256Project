package ru.ozon.route256.core_network_api

import com.squareup.moshi.Moshi

interface NetworkApi {

    fun getProductsApi(): ProductsApi

    fun getMoshi(): Moshi
}
