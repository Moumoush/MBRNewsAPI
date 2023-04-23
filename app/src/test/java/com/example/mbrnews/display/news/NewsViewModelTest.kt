package com.example.mbrnews.display.news

import com.example.mbrnews.display.ui.design_system.MBRArticleDetailState
import com.example.mbrnews.display.ui.design_system.MBRNewsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@DisplayName("NewsViewModel test :")
@org.junit.jupiter.api.extension.ExtendWith(NewsViewModelJUnitExtension::class)
class NewsViewModelTest {

    @org.junit.jupiter.api.Nested
    @DisplayName("Given no user interaction")
    inner class NoInteractionCase {
        @Nested
        @DisplayName("When screen is created")
        inner class OnScreenCreation {
            @Test
            @DisplayName("Then default values are ok")
            fun checkSessionDefaultValues(newsViewModel: NewsViewModel) {
                newsViewModel.checkDefaultValues()
            }
        }

        private fun NewsViewModel.checkDefaultValues() {
            Assertions.assertTrue(this.uiState.value.newsState is MBRNewsState.FirstLaunch)
            Assertions.assertTrue(this.uiState.value.articleState is MBRArticleDetailState.NONE)
        }

    }

    @org.junit.jupiter.api.Nested
    @DisplayName("Given load button clicked")
    inner class LoadButtonClicked {
        @Nested
        @DisplayName("When screen is ready")
        inner class OnButtonClicked {

            @Test
            @DisplayName("Then click trigger news loading")
            fun checkSessionDefaultValues(newsViewModel: NewsViewModel) {
                (newsViewModel.uiState.value.newsState as MBRNewsState.FirstLaunch).onClick()
                newsViewModel.checkFirstLoadState()
            }
        }

        private fun NewsViewModel.checkFirstLoadState() {
            Assertions.assertTrue(this.uiState.value.newsState is MBRNewsState.Success)
        }
    }
}