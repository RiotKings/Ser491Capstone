package com.example.robotcapstone.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.robotcapstone.R
import kotlinx.coroutines.delay


enum class BobcatFaceMode{
    IDLE_BLINKING,
    TALKING
}

enum class BobcatFaceDrawable(@DrawableRes val resId: Int) {
    STARES(R.drawable.idle_stares),
    BLINKS(R.drawable.idle_blinks),
    TALK(R.drawable.talking_open),
    LEFT(R.drawable.idle_looking_left),
    RIGHT(R.drawable.idle_looking_right)
}

@Composable
fun CatFace(
    mode: BobcatFaceMode,
    modifier: Modifier = Modifier,
    onFaceClick:() -> Unit = {}
) {
    var activeDrawable by remember { mutableStateOf(BobcatFaceDrawable.STARES) }
    LaunchedEffect(mode) {
        when (mode) {
            BobcatFaceMode.IDLE_BLINKING -> {
                while (true) {
                    activeDrawable = BobcatFaceDrawable.STARES
                    delay(3000) // open for 3 seconds
                    activeDrawable = BobcatFaceDrawable.BLINKS
                    delay(200)  // blink duration
                    activeDrawable = BobcatFaceDrawable.LEFT
                    delay(1000)
                    activeDrawable = BobcatFaceDrawable.BLINKS
                    delay(200)  // blink duration
                    activeDrawable = BobcatFaceDrawable.RIGHT
                    delay(1000)

                }
            }

            BobcatFaceMode.TALKING -> {
                while (true) {
                    activeDrawable = BobcatFaceDrawable.STARES  // mouth closed
                    delay(2000)
                    activeDrawable = BobcatFaceDrawable.TALK
                    delay(1000)  // mouth open
                    activeDrawable = BobcatFaceDrawable.BLINKS
                    delay(250)  // blink duration
                }
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D1826))
            .clickable { onFaceClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = activeDrawable.resId),
            contentDescription = "Bobcat Face",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
