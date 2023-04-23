package com.example.news_data.interceptor

import com.example.news_data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

/**
 * Used to add automatically api key needed for each request
 */
class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        //to avoid
        val url = request.url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
            .build()
        val requestBuilder: Request.Builder = request.newBuilder()
        val newRequest: Request = requestBuilder.url(url).build()
        return chain.proceed(newRequest)
    }

}