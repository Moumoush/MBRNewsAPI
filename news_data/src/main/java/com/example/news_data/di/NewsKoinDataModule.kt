package com.example.news_data.di

import com.example.news_data.BuildConfig
import com.example.news_data.NewsService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val newsDataModule = module {
    single<OkHttpClient>(named( "news_data_client")) {
        val interceptorLog by lazy {
            HttpLoggingInterceptor()
        }
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(interceptorLog)
        }

        okHttpClientBuilder.build()
    }

    single<NewsService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_API_BASE_URL)
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(
                    Schedulers.io()
                )
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get(named("news_data_client")))
            .build()
            .create(NewsService::class.java)
    }
}

