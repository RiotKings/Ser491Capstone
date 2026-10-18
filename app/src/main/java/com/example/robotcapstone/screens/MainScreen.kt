
package com.example.robotcapstone.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors
private val darkBlue = Color(0xFF001229)
private val panelBlue = Color(0xFF002044)
private val gold = Color(0xFFFFB900)
private val lightText = Color(0xFFE6EDF5)
private val red = Color(0xFFD00000)

// Placeholder information
private val tourStops = listOf(
    "Main Entrance",
    "Computer Science Labs",
    "Engineering Labs",
    "Student Project Area",
    "Exit"
)

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 20.dp,
                bottom = 30.dp
            )
    ) {
        Text(
            text = "SCE ROBOT TOUR GUIDE",
            color = gold,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Welcome to Quinnipiac University",
            color = lightText,
            fontSize = 18.sp
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Robot Status
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            panelBlue,
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            2.dp,
                            gold,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        "ROBOT STATUS",
                        color = gold,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text("Status: Ready", color = lightText, fontSize = 17.sp)
                    Text("Battery: 100%", color = lightText, fontSize = 17.sp)
                    Text("Location: Main Entrance", color = lightText, fontSize = 17.sp)
                }

                // Tour Information
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            panelBlue,
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            2.dp,
                            gold,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        "TOUR INFORMATION",
                        color = gold,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text("Estimated Time: 15 Minutes", color = lightText, fontSize = 17.sp)
                    Text("Number of Stops: ${tourStops.size}", color = lightText, fontSize = 17.sp)
                    Text("Current Stop: None", color = lightText, fontSize = 17.sp)
                }
            }

            // Tour Locations
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        panelBlue,
                        RoundedCornerShape(8.dp)
                    )
                    .border(
                        2.dp,
                        gold,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    "TOUR LOCATIONS",
                    color = gold,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                tourStops.forEachIndexed { index, location ->
                    Text(
                        text = "${index + 1}. $location",
                        color = lightText,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

        // Extra spacing above buttons
        Spacer(Modifier.height(32.dp))

        // Larger bottom control buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(2.dp, gold, RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = gold,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    "START TOUR",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, gold),
                contentPadding = PaddingValues(4.dp)
            ) {
                Text(
                    "PAUSE",
                    color = gold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, gold),
                contentPadding = PaddingValues(4.dp)
            ) {
                Text(
                    "RESUME",
                    color = gold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(2.dp, gold, RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = red,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "EMERGENCY STOP",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, gold),
                contentPadding = PaddingValues(4.dp)
            ) {
                Text(
                    "RESET",
                    color = lightText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
