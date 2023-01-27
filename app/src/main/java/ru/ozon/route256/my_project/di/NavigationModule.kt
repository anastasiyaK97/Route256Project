package ru.ozon.route256.my_project.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.my_project.PDPNavigationApiImpl
import ru.ozon.route256.my_project.ProductsNavigationApiImpl

@Module
interface NavigationModule {

    @Binds
    fun bindProductsNavigation(navigator: ProductsNavigationApiImpl): ProductsNavigationApi

    @Binds
    fun bindPDPNavigation(navigator: PDPNavigationApiImpl): PDPNavigationApi
}
