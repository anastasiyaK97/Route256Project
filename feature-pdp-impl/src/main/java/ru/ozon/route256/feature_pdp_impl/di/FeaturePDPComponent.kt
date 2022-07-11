package ru.ozon.route256.feature_pdp_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_prefs.api.PreferencesApi
import ru.ozon.route256.core_utils.di.FeatureScope
import ru.ozon.route256.feature_pdp_api.navigation.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment

@Component(
    modules = [DomainModule::class],
    dependencies = [FeatureProductDependencies::class]
)
@FeatureScope
interface FeaturePDPComponent {

    companion object {
        @Volatile
        var featurePDPComponent: FeaturePDPComponent? = null
            private set

        fun initAndGet(dependencies: FeatureProductDependencies): FeaturePDPComponent? {
            synchronized(FeaturePDPComponent::class) {
                if (featurePDPComponent == null) {
                    featurePDPComponent = DaggerFeaturePDPComponent.builder()
                        .featureProductDependencies(dependencies)
                        .build()
                }
                return featurePDPComponent
            }
        }

        fun get(): FeaturePDPComponent? {
            if (featurePDPComponent == null) {
                throw RuntimeException("You must call 'initAndGet(dependencies: FeatureProductDependencies)' method")
            }
            return featurePDPComponent
        }

        fun resetComponent() {
            featurePDPComponent = null
        }
    }

    fun inject(fragment: PDPFragment)

    @FeatureScope
    @Component(
        dependencies = [
            NetworkApi::class,
            PDPNavigationApi::class,
            PreferencesApi::class
        ]
    )
    interface FeatureProductDependenciesComponent : FeatureProductDependencies
}
