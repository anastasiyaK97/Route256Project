package ru.ozon.route256.feature_products_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindInteractor(interactor: ProductsInteractorImpl): ProductsInteractor
}
