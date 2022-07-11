package ru.ozon.route256.homework2.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.homework2.PDPNavigationApiImpl
import ru.ozon.route256.homework2.ProductsNavigationApiImpl

@Module
interface NavigationModule {

    @Binds
    fun bindProductsNavigation(navigator: ProductsNavigationApiImpl): ProductsNavigationApi

    @Binds
    fun bindPDPNavigation(navigator: PDPNavigationApiImpl): PDPNavigationApi
}
