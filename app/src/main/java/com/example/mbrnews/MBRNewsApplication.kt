package com.example.mbrnews

import android.app.Application
import com.example.news_domain.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MBRNewsApplication : Application() {

    private fun initializeInjector() {
        val fullDIModuleList = newsModule + newsDataModule
        startKoin{
            androidContext(this@MBRNewsApplication)
            modules(fullDIModuleList)
            createEagerInstances()
        }
    }
}