package ru.ozon.route256.core_network_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_network_api.CartApi
import ru.ozon.route256.core_network_impl.mock.Cart
import javax.inject.Singleton

@Module
abstract class CartModule {

    @Singleton
    @Binds
    abstract fun bindCart(impl: Cart): CartApi
}
