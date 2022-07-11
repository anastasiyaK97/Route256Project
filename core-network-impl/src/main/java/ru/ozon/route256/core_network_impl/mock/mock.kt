package ru.ozon.route256.core_network_impl.mock

import ru.ozon.route256.core_network_api.model.product.ProductDTO
import ru.ozon.route256.core_network_api.model.product.ProductInListDTO

val cart = hashMapOf<String, Int>()

val productInListDTOs = mutableListOf(
    ProductInListDTO(
        guid = "b5f5852b-3b8c-4857-9f53-ac5c2b6a3b2f",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6131838211.jpg"),
        name = "Борщ по-домашнему",
        price = "199",
        rating = 4.6,
        isFavorite = false,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "5cbbb7d0-e9c6-4932-8d85-79313c58e465",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-u/6255350190.jpg"),
        name = "Ваш любимый Сальчичон",
        price = "79",
        rating = 5.0,
        isFavorite = true,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "cc87f70d-7570-42ee-a221-db8887534896",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg"),
        name = "Бельгийская смесь \"Мираторг\"",
        price = "149",
        rating = 3.2,
        isFavorite = false,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "627de4ca-b4fd-46ea-9201-562448af6fc1",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-f/6199943655.jpg"),
        name = "Джем Ozon Express",
        rating = 4.6,
        price = "1390",
        isFavorite = false,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "493ef2ba-cd2f-4ca5-b3af-f9091115700e",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6267606395.jpg"),
        name = "Мармелад клюква с ягелем",
        rating = 2.8,
        price = "319",
        isFavorite = true,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "ceadee7a-9d10-4987-8ed3-0624d6fbe5c0",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-8/6288053576.jpg"),
        name = "Авокадо Хасс",
        price = "600",
        rating = 4.5,
        isFavorite = false,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "c3cfe1a8-6eec-4e9f-a260-490e128763f4",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-4/6099200308.jpg"),
        name = "Молоко",
        price = "65",
        rating = 5.0,
        isFavorite = true,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "a9cd4415-48b0-4557-8f29-6d28824fe91b",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6096233435.jpg"),
        name = "Мороженое сорбет",
        price = "110",
        rating = 4.0,
        isFavorite = false,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "856c1c90-1b8e-46ba-a5de-bc818dc1bdbd",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-d/6206219785.jpg"),
        name = "Сырок творожный",
        price = "23",
        rating = 5.0,
        isFavorite = true,
        isInCart = false
    ),
    ProductInListDTO(
        guid = "ebe799f5-6e01-45dc-8139-e714753896c1",
        image = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6167822191.jpg"),
        name = "Блинчики домашние",
        price = "189",
        rating = 3.0,
        isFavorite = false,
        isInCart = false
    ),
)

val productsDTOs = listOf(
    ProductDTO(
        guid = "b5f5852b-3b8c-4857-9f53-ac5c2b6a3b2f",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6131838211.jpg"),
        name = "Борщ по-домашнему",
        price = "199",
        rating = 4.6,
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "5cbbb7d0-e9c6-4932-8d85-79313c58e465",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-u/6255350190.jpg"),
        name = "Ваш любимый Сальчичон",
        price = "79",
        rating = 5.0,
        isFavorite = true,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "cc87f70d-7570-42ee-a221-db8887534896",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg"),
        name = "Бельгийская смесь \"Мираторг\"",
        price = "149",
        rating = 3.2,
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "627de4ca-b4fd-46ea-9201-562448af6fc1",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-f/6199943655.jpg"),
        name = "Джем Ozon Express",
        rating = 4.6,
        price = "1390",
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "493ef2ba-cd2f-4ca5-b3af-f9091115700e",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6267606395.jpg"),
        name = "Мармелад клюква с ягелем",
        rating = 2.8,
        price = "319",
        isFavorite = true,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "ceadee7a-9d10-4987-8ed3-0624d6fbe5c0",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-8/6288053576.jpg"),
        name = "Авокадо Хасс",
        price = "600",
        rating = 4.5,
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "c3cfe1a8-6eec-4e9f-a260-490e128763f4",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-4/6099200308.jpg"),
        name = "Молоко",
        price = "65",
        rating = 5.0,
        isFavorite = true,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "a9cd4415-48b0-4557-8f29-6d28824fe91b",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6096233435.jpg"),
        name = "Мороженое сорбет",
        price = "110",
        rating = 4.0,
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "856c1c90-1b8e-46ba-a5de-bc818dc1bdbd",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-d/6206219785.jpg"),
        name = "Сырок творожный",
        price = "23",
        rating = 5.0,
        isFavorite = true,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
    ProductDTO(
        guid = "ebe799f5-6e01-45dc-8139-e714753896c1",
        images = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6167822191.jpg"),
        name = "Блинчики домашние",
        price = "189",
        rating = 3.0,
        isFavorite = false,
        isInCart = false,
        weight = 2.0,
        count = 0,
        availableCount = 2,
        description = "Свежий и вкусный товар",
        additionalParams = mapOf()
    ),
)
