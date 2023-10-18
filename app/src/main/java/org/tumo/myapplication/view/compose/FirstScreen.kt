package org.tumo.myapplication.view.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun FirstView(navController: NavController) {
    // Your UI for the first view
    Button(onClick = { navController.navigate("secondView") }) {
        Text(text = "Go to Second View")
    }
}