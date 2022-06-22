package ru.ozon.route256.core_prefs.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @ProductsPrefs
    @Singleton
    fun provideProductsSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("PRODUCTS_DATA", Context.MODE_MULTI_PROCESS)

    @Provides
    @ProductsDetailsPrefs
    @Singleton
    fun provideProductDetailsSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("PRODUCTS_DETAILED_DATA", Context.MODE_MULTI_PROCESS)

}
