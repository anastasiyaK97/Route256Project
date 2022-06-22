package ru.ozon.route256.core_utils.workers

import androidx.work.BackoffPolicy
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

object WorkerUtils {

    const val PRODUCTS_TAG = "Products worker"
    const val PRODUCTS_DETAILED_TAG = "Products detail worker"
    const val PRODUCTS_CHAIN_TAG = "Products chain"

    fun getOneTimeRequest(clazz: Class<out ListenableWorker>, tag: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(clazz)
            .addTag(tag)
            .addTag(PRODUCTS_CHAIN_TAG)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.SECONDS
            )
            .build()
    }
}
