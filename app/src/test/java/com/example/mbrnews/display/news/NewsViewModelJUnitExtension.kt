package com.example.mbrnews.display.news

import androidx.lifecycle.viewModelScope
import com.example.news_domain.model.Articles
import com.example.news_domain.news.NewsDataRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.*
import org.mockito.Mockito
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
class NewsViewModelJUnitExtension(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) :  BeforeEachCallback, AfterEachCallback,ParameterResolver{
    override fun beforeEach(context: ExtensionContext) {
        Dispatchers.setMain(testDispatcher)
        val immediate: Scheduler = object : Scheduler() {

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run!!, 0, unit!!)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { obj: Runnable -> obj.run() }, true)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
    }

    override fun supportsParameter(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext
    ): Boolean {
        return parameterContext.parameter.type==NewsViewModel::class.java
    }

    override fun resolveParameter(
        parameterContext: ParameterContext?,
        extensionContext: ExtensionContext?
    ): Any {
        val newsDataRepository = Mockito.mock(NewsDataRepository::class.java)
        Mockito.`when`(newsDataRepository.loadEveryArticle()).thenReturn(Single.just(Articles(
            listOf()
        )))
        Mockito.`when`(newsDataRepository.loadEveryArticle()).thenReturn(Single.just(Articles(
            listOf()
        )))
        val newsViewModel = NewsViewModel(newsDataRepository)
        newsViewModel.viewModelScope.launch {
            newsViewModel.uiState.collect()
        }
        return newsViewModel
    }

    override fun afterEach(context: ExtensionContext) {
        Dispatchers.resetMain()
    }
}
