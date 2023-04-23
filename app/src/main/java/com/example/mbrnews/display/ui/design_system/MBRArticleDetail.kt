package com.example.mbrnews.display.ui.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.news_domain.model.Article

open class MBRArticleDetailState {
    class NONE : MBRArticleDetailState()
    class Display(val article: Article) : MBRArticleDetailState()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MBRArticleDetail(detail: MBRArticleDetailState.Display) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(Modifier.width(340.dp), shape = RoundedCornerShape(8.dp)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "title: ")
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = detail.article.title)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "id: ")
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = detail.article.id)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "author: ")
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = detail.article.author)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "name: ")
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = detail.article.name)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "content: ")
                        Text(text = detail.article.content)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "description: ")
                        Text(text = detail.article.description)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "publishedAt: ")
                        Text(text = detail.article.publishedAt)
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(text = "url: ")
                        Text(text = detail.article.url)
                    }


                }

            }

        }
    }
}