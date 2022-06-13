package ru.ozon.route256.homework2.di

import ru.ozon.route256.homework2.data.repositoriesImpl.MockProductsRepositoryImpl
import ru.ozon.route256.homework2.domain.interactors.ProductsInteractor
import ru.ozon.route256.homework2.domain.interactors.ProductsInteractorImpl

 // Подробнее можете почитать [тут](http://sergeyteplyakov.blogspot.com/2013/03/di-service-locator.html),
 // [тут](https://habr.com/ru/post/465395/) и [тут](https://javatutor.net/articles/j2ee-pattern-service-locator).

class ServiceLocator {
    val productsInteractor: ProductsInteractor by lazy {
        ProductsInteractorImpl(
            MockProductsRepositoryImpl()
        )
    }
}
