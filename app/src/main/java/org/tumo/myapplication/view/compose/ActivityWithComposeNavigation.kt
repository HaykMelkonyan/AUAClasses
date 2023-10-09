package org.tumo.myapplication.view.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tumo.myapplication.R
import org.tumo.myapplication.ui.theme.MyApplicationTheme
import org.tumo.myapplication.view.xml.ActivityWithFragmentNavigation
import org.tumo.myapplication.viewmodel.DataLoaderViewModel


class ActivityWithComposeNavigation : ComponentActivity() {
    val viewModel: DataLoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        MainScreen()
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
                        DataLoader()
                    }
                }
            }
        }
    }

    @Composable
    private fun DataLoader() {
        val result = viewModel.liveDataNames.observeAsState(listOf())
        Column {
            Button(onClick = {
                viewModel.loadData()
            }) {
                Text(text = getString(R.string.load_some_data))
            }
            DataComponent(result.value)
        }
    }


    @Composable
    private fun DataComponent(names: List<String>) {
        LazyColumn {
            items(names.size) {
                Text(text = names[it])
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "firstView") {
        composable("firstView") { FirstView(navController) }
        composable("secondView") { SecondView(navController) }
    }
}

@Composable
fun FirstView(navController: NavController) {
    // Your UI for the first view
    Button(onClick = { navController.navigate("secondView") }) {
        Text(text = "Go to Second View")
    }
}

@Composable
fun SecondView(navController: NavController) {
    // Your UI for the second view
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "Back to First View")
    }
}
