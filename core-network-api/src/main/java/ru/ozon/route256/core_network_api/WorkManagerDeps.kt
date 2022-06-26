package ru.ozon.route256.core_network_api

import androidx.work.WorkManager

interface WorkManagerDeps {

    fun workManager(): WorkManager
}
