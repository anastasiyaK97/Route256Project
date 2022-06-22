package ru.ozon.route256.core_network_impl.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.ozon.route256.core_network_api.ProductsApi
import ru.ozon.route256.core_network_impl.BuildConfig
import ru.ozon.route256.core_network_impl.interceptor.ExceptionsInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CoreNetworkModule {

    companion object {
        private const val TIMEOUT = 30L
    }

    @Provides
    @ApiUrl
    fun provideApiUrl() = "https://run.mocky.io/"

    @Singleton
    @Provides
    fun provideHttpClient(
        exceptionsInterceptor: ExceptionsInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(exceptionsInterceptor)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
        }
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun buildRetrofitInstance(
        client: OkHttpClient,
        moshi: Moshi,
        @ApiUrl apiUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideProductsApi(retrofit: Retrofit) = retrofit.create(ProductsApi::class.java)
}
