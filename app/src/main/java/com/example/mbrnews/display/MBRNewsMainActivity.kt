package com.example.mbrnews.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mbrnews.display.news.NewsViewModel
import com.example.mbrnews.display.ui.theme.MBRNewsAPITheme
import org.koin.android.ext.android.inject

class MBRNewsMainActivity : ComponentActivity() {
    private val newsViewModel : NewsViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(newsViewModel)
        setContent {
            MBRNewsAPITheme {
                // A surface container using the 'background' color from the theme
                val state = newsViewModel.uiState.collectAsState().value
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onDestroy() {
        lifecycle.removeObserver(newsViewModel)
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MBRNewsAPITheme {
        Greeting("Android")
    }
}