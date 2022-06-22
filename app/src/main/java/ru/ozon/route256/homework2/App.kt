package ru.ozon.route256.homework2

import android.app.Application
import ru.ozon.route256.homework2.di.ApplicationComponent
import ru.ozon.route256.homework2.di.DaggerApplicationComponent

class App: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = ApplicationComponent.initAndGet(this)
            ?: throw Exception("appComponent not initialized")
    }
}
