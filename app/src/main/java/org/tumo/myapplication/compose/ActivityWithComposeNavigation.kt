package org.tumo.myapplication.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tumo.myapplication.R
import org.tumo.myapplication.ui.theme.MyApplicationTheme
import org.tumo.myapplication.xml.ActivityWithFragmentNavigation

class MainActivity : ComponentActivity() {
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
                            startActivity(Intent(baseContext, ActivityWithFragmentNavigation::class.java))
                        }) {
                            Text(text = getString(R.string.start_xml_activity))
                        }
                    }
                }
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

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}