package com.example.mbrnews

import android.app.Application
import com.example.mbrnews.di.applicationModule
import com.example.news_domain.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MBRNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        val fullDIModuleList = applicationModule + newsModule
        startKoin{
            androidContext(this@MBRNewsApplication)
            modules(fullDIModuleList)
            createEagerInstances()
        }
    }
}