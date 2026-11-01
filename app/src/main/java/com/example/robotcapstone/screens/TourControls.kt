package com.example.robotcapstone.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.example.robotcapstone.navigation.TourController
import com.example.robotcapstone.navigation.TourState

@Composable
fun TourControls(tour: TourController, modifier: Modifier = Modifier) {
    Column(modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Stop ${tour.currentStop + 1} of ${tour.totalStops} · ${tour.state}")
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            when (tour.state) {
                TourState.READY  -> Button(onClick = tour::startTour)  { Text("Start tour") }
                TourState.ACTIVE -> Button(onClick = tour::pauseTour)  { Text("Pause tour") }
                TourState.PAUSED -> Button(onClick = tour::resumeTour) { Text("Resume tour") }
            }
        }
    }
}