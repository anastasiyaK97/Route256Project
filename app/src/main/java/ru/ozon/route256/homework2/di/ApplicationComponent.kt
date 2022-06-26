package ru.ozon.route256.homework2.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.core_network_api.WorkManagerDeps
import ru.ozon.route256.core_prefs.api.PreferencesApi
import ru.ozon.route256.core_prefs.di.PreferencesModule
import ru.ozon.route256.homework2.App
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PreferencesModule::class])
interface ApplicationComponent:
    PreferencesApi,
    WorkManagerDeps {

    companion object {
        @Volatile
        var appComponent: ApplicationComponent? = null
            private set

        fun initAndGet(context: Context): ApplicationComponent? {
            synchronized(ApplicationComponent::class) {
                if (appComponent == null) {
                    appComponent = DaggerApplicationComponent.factory()
                        .create(context)
                }
                return appComponent
            }
        }
    }

        fun inject(application: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}