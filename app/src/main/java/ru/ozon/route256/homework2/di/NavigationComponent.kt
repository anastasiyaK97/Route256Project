package ru.ozon.route256.homework2.di

import dagger.Component
import ru.ozon.route256.core_navigation_api.NavigationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent: NavigationApi
