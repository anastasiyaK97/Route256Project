package ru.ozon.route256.core_utils.workers

import androidx.work.BackoffPolicy
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import java.util.concurrent.TimeUnit

object WorkerUtils {

    private const val PRODUCTS_CHAIN_TAG = "TAG_PRODUCTS_CHAIN"

    fun getOneTimeRequest(clazz: Class<out ListenableWorker>, tag: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(clazz)
            .addTag(tag)
            .addTag(PRODUCTS_CHAIN_TAG)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
    }
}
