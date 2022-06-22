package ru.ozon.route256.core_network_api.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDTO(
    val guid: String,
    val name: String,
    val price: String,
    val images: List<String>,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val count: Int,
    val availableCount: Int,
    val weight: Double?,
    val description: String,
    val additionalParams: Map<String, String>
)
