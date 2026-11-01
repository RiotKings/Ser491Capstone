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

    // Simulated robot for now; swap SimulatedRobot for the real ROS 2 controller later
    val scope = rememberCoroutineScope()
    val tour = remember { TourController(SimulatedRobot(scope), totalStops = 5) }

    LaunchedEffect(tour.state, tour.currentStop) {
        android.util.Log.d("Tour", "state=${tour.state} stop=${tour.currentStop}")
    }

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
                status = when (tour.state) {
                    TourState.READY -> "Ready"
                    TourState.ACTIVE -> "Touring"
                    TourState.PAUSED -> "Paused"
                },
                currentStopIndex = if (tour.state == TourState.READY) null else tour.currentStop,
                onStartTour = {
                    // Start only begins a new tour
                    if (tour.state == TourState.READY) {
                        tour.startTour()
                        currentScreen = ScreenState.FACE_TALKING
                    }
                },
                onPauseTour = {
                    // REQ-3 / REQ-4: stop the robot and keep progress
                    if (tour.state == TourState.ACTIVE) {
                        tour.pauseTour()
                        currentScreen = ScreenState.FACE_IDLE
                    }
                },
                onResumeTour = {
                    // REQ-5: continue from the stop where it paused
                    if (tour.state == TourState.PAUSED) {
                        tour.resumeTour()
                        currentScreen = ScreenState.FACE_TALKING
                    }
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