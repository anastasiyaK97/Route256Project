package ru.ozon.route256.feature_pdp_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractor
import ru.ozon.route256.feature_pdp_impl.domain.interactor.ProductInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindInteractor(interactor: ProductInteractorImpl): ProductInteractor
}
