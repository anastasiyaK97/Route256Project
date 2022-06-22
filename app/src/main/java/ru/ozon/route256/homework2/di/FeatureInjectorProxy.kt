package ru.ozon.route256.homework2.di

import ru.ozon.route256.core_network_impl.di.DaggerCoreNetworkComponent
import ru.ozon.route256.feature_pdp_impl.di.DaggerFeaturePDPComponent_FeatureProductDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.FeaturePDPComponent
import ru.ozon.route256.feature_products_impl.di.DaggerFeatureProductsComponent_FeatureProductsDependenciesComponent
import ru.ozon.route256.feature_products_impl.di.FeatureProductsComponent

object FeatureInjectorProxy {

    fun initFeatureProductsDI() {
        FeatureProductsComponent.initAndGet(
            DaggerFeatureProductsComponent_FeatureProductsDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productsNavigationApi(DaggerNavigationComponent.builder().build().getProductsNavigationApi())
                .preferencesApi(ApplicationComponent.appComponent)
                .build()
        )
    }

    fun initFeaturePDPDI() {
        FeaturePDPComponent.initAndGet(
            DaggerFeaturePDPComponent_FeatureProductDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .pDPNavigationApi(DaggerNavigationComponent.builder().build().getPDPNavigationApi())
                .preferencesApi(ApplicationComponent.appComponent)
                .build()
        )
    }
}
