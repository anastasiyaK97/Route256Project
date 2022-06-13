package ru.ozon.route256.feature_pdp_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.data.repository_impl.ProductRepositoryImpl
import ru.ozon.route256.feature_pdp_impl.domain.repository.ProductRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(repo: ProductRepositoryImpl): ProductRepository
}
