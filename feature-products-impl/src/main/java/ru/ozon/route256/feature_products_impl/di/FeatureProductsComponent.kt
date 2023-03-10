package ru.ozon.route256.feature_products_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_network_api.WorkManagerDeps
import ru.ozon.route256.core_prefs.api.PreferencesApi
import ru.ozon.route256.core_utils.di.FeatureScope
import ru.ozon.route256.feature_products_api.navigation.ProductsNavigationApi
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetDetailedProductsWorker
import ru.ozon.route256.feature_products_impl.data.repository_impl.GetProductsWorker
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment

@FeatureScope
@Component(
    modules = [DomainModule::class],
    dependencies = [FeatureProductsDependencies::class]
)
interface FeatureProductsComponent {

    companion object {
        @Volatile
        var featureProductComponent: FeatureProductsComponent? = null
            private set

        fun initAndGet(dependencies: FeatureProductsDependencies): FeatureProductsComponent? {
            synchronized(FeatureProductsComponent::class) {
                if (featureProductComponent == null) {
                    featureProductComponent = DaggerFeatureProductsComponent.builder()
                        .featureProductsDependencies(dependencies)
                        .build()
                }
                return featureProductComponent
            }
        }

        fun get(): FeatureProductsComponent? {
            if (featureProductComponent == null) {
                throw RuntimeException("You must call 'initAndGet(dependencies: FeatureProductsDependencies)' method")
            }
            return featureProductComponent
        }

        fun resetComponent() {
            featureProductComponent = null
        }
    }

    fun inject(fragment: ProductsFragment)

    fun inject(worker: GetProductsWorker)
    fun inject(worker: GetDetailedProductsWorker)

    @FeatureScope
    @Component(
        dependencies = [
            NetworkApi::class,
            ProductsNavigationApi::class,
            PreferencesApi::class,
            WorkManagerDeps::class
        ]
    )
    interface FeatureProductsDependenciesComponent : FeatureProductsDependencies
}
