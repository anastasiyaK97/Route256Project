package ru.ozon.route256.my_project.di

import dagger.Component
import ru.ozon.route256.core_navigation_api.NavigationApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent : NavigationApi
