package ru.ozon.route256.core_network_impl.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import ru.ozon.route256.core_network_api.model.error.ApiErrorType
import ru.ozon.route256.core_network_api.model.error.ServerException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionsInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .proceed(chain.request())
        .also { getError(it, it.body())?.let { exception -> throw exception } }

    private fun getError(response: Response, body: ResponseBody?): IOException? = body
        ?.let { _ ->
            when {
                response.code() != 200 -> {
                    val code = response.code()
                    when {
                        code >= 500 -> ServerException(ApiErrorType.SERVER_ERROR)
                        else -> ServerException(ApiErrorType.GENERAL_ERROR)
                    }
                }
                else -> null
            }
        }
}
