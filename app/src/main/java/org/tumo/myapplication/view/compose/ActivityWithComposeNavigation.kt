package org.tumo.myapplication.view.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import org.tumo.myapplication.R
import org.tumo.myapplication.repository.NewsResponse
import org.tumo.myapplication.ui.theme.MyApplicationTheme
import org.tumo.myapplication.view.xml.ActivityWithFragmentNavigation
import org.tumo.myapplication.viewmodel.DataLoaderViewModel


class ActivityWithComposeNavigation : ComponentActivity() {
    private val viewModel: DataLoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun MainScreen() {
        Column {
            MainNavScreen()
            NavigationXml()
            DataLoader()
        }
    }

    @Composable
    private fun NavigationXml() {
        Button(onClick = {
            startActivity(
                Intent(
                    baseContext,
                    ActivityWithFragmentNavigation::class.java
                )
            )
        }) {
            Text(text = getString(R.string.start_xml_activity))
        }
    }

    @Composable
    private fun DataLoader() {
        val result = viewModel.liveDataNames.observeAsState(listOf())
        val images = viewModel.liveDataImages.observeAsState(listOf())
        val news = viewModel.liveDataNews.observeAsState()
        val sliderValue = remember { mutableFloatStateOf(0f) }
        Column {
            Button(onClick = {
                viewModel.loadData()
                viewModel.loadImages()
                viewModel.loadNews()
            }) {
                Text(text = getString(R.string.load_some_data))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Slider(
                value = sliderValue.floatValue,
                valueRange = 0f..100f,
                modifier = Modifier.padding(10.dp),
                onValueChange = {
                    sliderValue.floatValue = it
                })
            Spacer(Modifier.height(10.dp))
            Text(text = sliderValue.floatValue.toString())
            DataComponent(news.value)


        }
    }


    @Composable
    private fun DataComponent(news: NewsResponse?) {
        LazyColumn {
            news?.articles?.let { articles ->
                items(articles.size) { index ->
                    Card {
                        Text(text = articles[index].author?:"")
                        AsyncImage(model = articles[index].urlToImage?:"", contentDescription = "")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }
}

@Composable
fun MainNavScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "firstView") {
        composable("firstView") { FirstView(navController) }
        composable("secondView") { SecondView(navController) }
    }
}
