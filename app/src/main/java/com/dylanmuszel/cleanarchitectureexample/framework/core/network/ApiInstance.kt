package com.dylanmuszel.cleanarchitectureexample.framework.core.network

import com.dylanmuszel.cleanarchitectureexample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInstance @Inject constructor() {

    private var _retrofit: Retrofit? = null
    private val retrofit: Retrofit
        get() = _retrofit ?: run {
            _retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .apply {
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                        val httpClient = OkHttpClient.Builder().apply {
                            addInterceptor(logging)
                        }
                        client(httpClient.build())
                    }
                }
                .build()
            _retrofit!!
        }

    fun <T> getService(clazz: Class<T>): T = retrofit.create(clazz)

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}