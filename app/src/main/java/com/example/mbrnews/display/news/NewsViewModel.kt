package com.example.mbrnews.display.news

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
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent

/**
 * View model responsible of the display of the articles on screen and reacts to all article related
 *
 * events.
 */
class NewsViewModel(val newsRepository : NewsRepository): ViewModel(), KoinComponent,
    DefaultLifecycleObserver{
    private var compositeDisposable = CompositeDisposable()

    private val firstLaunchListener =  {
        loadArticles()
    }
    private val _newsState = MutableStateFlow<MBRNewsState>(MBRNewsState.FirstLaunch(firstLaunchListener))
    private val _articleState = MutableStateFlow<MBRArticleDetailState>(MBRArticleDetailState.NONE())

    val uiState: StateFlow<MBRNewsState> = combine(_newsState,_articleState){ news, article ->
        NewsUIState(news, article)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        MBRNewsState.FirstLaunch(firstLaunchListener)
    )

    data class NewsUIState(var newsState : MBRNewsState = MBRNewsState.FirstLaunch(firstLaunchListener), var articleState: MBRArticleDetailState = MBRArticleDetailState.NONE())
    override fun onStart(owner: LifecycleOwner) {
        if(compositeDisposable.isDisposed){
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


    var onArticleClick: (String)-> Unit = { articleIdChoosed ->
        _newsState.value.let { previousState ->
            if(previousState is MBRNewsState.Success){
                previousState.articles.articles.firstOrNull {
                    it.id == articleIdChoosed
                }
            }
        }
        if(_newsState.value is MBRNewsState.Success &&  _newsState.value.articles.articles.map { it.id }.contains(articleChoosed)){

        }
    }
    private fun loadArticles() {
        newsRepository.loadEveryArticle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {articles ->
                    _newsState.update { previousState ->
                        MBRNewsState.Success(articles, )
                    }
                }
            ).addTo(compositeDisposable)
    }

}