package com.example.mbrnews.display.ui.design_system

import androidx.compose.runtime.Composable
import com.example.news_domain.model.Article

open class MBRArticleDetailState{
    class NONE : MBRArticleDetailState()
    class Display(article: Article) : MBRArticleDetailState()
}

@Composable
fun MBRArticleDetail(detail : MBRArticleDetailState.Display){

}