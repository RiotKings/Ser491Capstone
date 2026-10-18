package com.example.robotcapstone.navigation

import androidx.compose.runtime.*
import com.example.robotcapstone.screens.BobcatFaceMode
import com.example.robotcapstone.screens.CatFace
import com.example.robotcapstone.screens.MainScreen

enum class ScreenState {
    FACE_IDLE,
    DASHBOARD,
    FACE_TALKING
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(ScreenState.FACE_IDLE) }

    when (currentScreen) {
        ScreenState.FACE_IDLE -> {
            CatFace(
                mode = BobcatFaceMode.IDLE_BLINKING,
                onFaceClick = {
                    currentScreen = ScreenState.DASHBOARD
                }
            )
        }
        ScreenState.DASHBOARD -> {
            MainScreen(
                onStartTour = {
                    currentScreen = ScreenState.FACE_TALKING
                },
                onPauseTour = {
                    currentScreen = ScreenState.FACE_IDLE
                }
            )
        }
        ScreenState.FACE_TALKING -> {
            CatFace(
                mode = BobcatFaceMode.TALKING,
                onFaceClick = {
                    currentScreen = ScreenState.DASHBOARD
                }
            )
        }
    }
}