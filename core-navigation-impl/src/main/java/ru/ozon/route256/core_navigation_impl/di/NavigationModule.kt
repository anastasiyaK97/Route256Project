package ru.ozon.route256.core_navigation_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_navigation_impl.navigation.PDPNavigationApiImpl
import ru.ozon.route256.core_navigation_impl.navigation.ProductsNavigationApiImpl
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi

@Module
interface NavigationModule {

    @Binds
    fun bindProductsNavigation(navigator: ProductsNavigationApiImpl): ProductsNavigationApi

    @Binds
    fun bindPDPNavigation(navigator: PDPNavigationApiImpl): PDPNavigationApi
}
