package com.example.mbrnews.display.news

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mbrnews.display.ui.design_system.MBRArticleDetailState
import com.example.mbrnews.display.ui.design_system.MBRNewsState
import com.example.news_domain.NewsRepository
import com.example.news_domain.model.Articles
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent

/**
 * View model responsible of the display of the articles on screen and reacts to all article related events.
 *
 *
 *
 * @param newsRepository [NewsRepository] repository used to retrieve data related to news
 * @property uiState state used to refresh ui display is composed by States provided by the view components
 */
class NewsViewModel(val newsRepository: NewsRepository) : ViewModel(), KoinComponent,
    DefaultLifecycleObserver {

    private var compositeDisposable = CompositeDisposable()

    private val firstLaunchListener = {
        loadArticles()
    }
    private val _newsState =
        MutableStateFlow<MBRNewsState>(MBRNewsState.FirstLaunch(firstLaunchListener))
    private val _articleState =
        MutableStateFlow<MBRArticleDetailState>(MBRArticleDetailState.NONE())

    val uiState: StateFlow<NewsUIState> = combine(_newsState, _articleState) { news, article ->
        NewsUIState(news, article)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        NewsUIState()
    )


    override fun onStart(owner: LifecycleOwner) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        loadArticles()
    }

    override fun onStop(owner: LifecycleOwner) {
        compositeDisposable.dispose()
        super.onStop(owner)
    }


    private var onArticleClick: (Int) -> Unit = { articleIndexChosen ->
        _newsState.value.let { previousState ->
            if (previousState is MBRNewsState.Success && articleIndexChosen < previousState.articles.articles.size) {
                previousState.articles
                    .searchArticleFromItsId(articleIndexChosen)
                    .let { chosenArticle ->
                        _articleState.update {
                            MBRArticleDetailState.Display(chosenArticle)
                        }
                    }
            }
        }
    }

    private fun Articles.searchArticleFromItsId(articleIndexChosen: Int) = articles[articleIndexChosen]

    private fun loadArticles() {
        _newsState.update {
            MBRNewsState.Loading()
        }
        newsRepository.loadEveryArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { articles ->
                    _newsState.update {
                        MBRNewsState.Success(articles, onArticleClick)
                    }
                },
                onError = {
                    Log.d("ERROR", it.message?:"")
                }
            ).addTo(compositeDisposable)
    }

    inner class NewsUIState(
        val newsState: MBRNewsState = MBRNewsState.FirstLaunch(firstLaunchListener),
        val articleState: MBRArticleDetailState = MBRArticleDetailState.NONE()
    )
}