package com.apps.demo.apixuweather.utils

import com.apps.demo.apixuweather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val oldRequest = chain.request()
        val updatedURL = oldRequest.url().newBuilder().addEncodedQueryParameter("key", BuildConfig.APIXU_SECRET).build()
        return chain.proceed(oldRequest.newBuilder().url(updatedURL).build())
    }
}