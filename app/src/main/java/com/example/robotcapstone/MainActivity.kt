
package com.example.robotcapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview
import com.example.robotcapstone.navigation.AppNavigation
import com.example.robotcapstone.screens.MainScreen
import com.example.robotcapstone.ui.theme.RobotCapstoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RobotCapstoneTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun MainScreenPreview() {
    RobotCapstoneTheme {
        MainScreen()
    }
}
