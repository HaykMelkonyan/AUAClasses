package org.tumo.myapplication.view.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun SecondView(navController: NavController) {
    // Your UI for the second view
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "Back to First View")
    }
}
