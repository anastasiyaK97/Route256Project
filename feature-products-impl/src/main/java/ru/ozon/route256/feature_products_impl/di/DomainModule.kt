package ru.ozon.route256.feature_products_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_products_impl.data.repository_impl.ProductsRepositoryImpl
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractor
import ru.ozon.route256.feature_products_impl.domain.interactor.ProductsInteractorImpl
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository

@Module
interface DomainModule {

    @Binds
    fun bindRepository(repo: ProductsRepositoryImpl): ProductsRepository

    @Binds
    fun bindInteractor(interactor: ProductsInteractorImpl): ProductsInteractor
}
