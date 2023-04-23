package com.example.mbrnews.display.ui.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.news_domain.model.Articles

open class MBRNewsState{
    data class FirstLaunch(
        val onClick : () -> Unit
    ) : MBRNewsState()
    class Loading : MBRNewsState()
    data class Success(
        val articles: Articles,
        val onArticleSelected : (id : Int) -> Unit
    ) : MBRNewsState()
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MBRNewsList(state: MBRNewsState){
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (state is MBRNewsState.FirstLaunch) {
                Button(onClick = { state.onClick.invoke() }) {
                    Text(text = "Load News List")
                }
            }
            if (state is MBRNewsState.Success) {
                LazyColumn(Modifier.fillMaxWidth()) {
                    itemsIndexed(state.articles.articles) { index, item ->
                        state.articles.articles.forEach {
                            Row(Modifier.height(200.dp)) {
                                Text(text = it.title)
                                GlideImage(model = it.urlToImage,
                                    modifier = Modifier
                                        .padding(24.dp)
                                        .clickable(onClick = { state.onArticleSelected.invoke(index) })
                                        .fillParentMaxSize(),
                                    contentDescription = item.title
                                )
                            }
                        }
                    }
                }
            }
        }
        if(state is MBRNewsState.Loading){
            Surface(color = Color.Black, modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(64.dp))
            }
        }
    }



}